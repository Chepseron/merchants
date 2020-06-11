package com.github.adminfaces.starter.bean;

import com.amon.db.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.Base64;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.MeterGaugeChartModel;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "Merchants")
@SessionScoped
public class Merchants implements Serializable {

    @PersistenceContext(name = "merchantsPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private TbUser user = new TbUser();
    private List<TbUser> userList = new ArrayList();

    private Audit audit = new Audit();
    private List<Audit> auditList = new ArrayList();

    private TbUmimerchants merchants = new TbUmimerchants();
    private List<TbUmimerchants> merchantsList = new ArrayList();

    private Status status = new Status();
    private List<Status> statusList = new ArrayList();

    private TbBranch branch = new TbBranch();
    private List<TbBranch> branchList = new ArrayList();

    private TbUmipayments payments = new TbUmipayments();
    private List<TbUmipayments> paymentsList = new ArrayList();

    private TbTransaction transactions = new TbTransaction();
    private List<TbTransaction> transactionsList = new ArrayList();

    private String[] selectedResponsibilities = new String[30];
    private HtmlDataTable htmlDataTable = new HtmlDataTable();

    private String citizenShip = new String();
    private String error = new String();
    private String otp;

    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private MeterGaugeChartModel assignedMeter;
    private MeterGaugeChartModel unassignedMeter;

    private String newPassword = new String();
    private String confirmPword = new String();
    private String username = new String();
    private String password = new String();

    private boolean group = false;
    private boolean audittrails = false;
    private boolean iprsTransactionsView = false;
    private boolean iprsQuery = false;
    private boolean rolesView = false;
    private boolean userCreateView = false;

    public Merchants() {
    }

    @PostConstruct
    public void init() {
        try {
            createMeterGaugeModels();
            setEventModel(new DefaultScheduleModel());
            getEventModel().addEvent(new DefaultScheduleEvent("assign cards to accounts", previousDay8Pm(), previousDay11Pm()));
            getEventModel().addEvent(new DefaultScheduleEvent("assign cards to accounts", today1Pm(), today6Pm()));
            getEventModel().addEvent(new DefaultScheduleEvent("assign cards to accounts", nextDay9Am(), nextDay11Am()));
            getEventModel().addEvent(new DefaultScheduleEvent("assign cards to accounts", theDayAfter3Pm(), fourDaysLater3pm()));
            this.setLazyEventModel(new LazyScheduleModel() {
                public void loadEvents(Date start, Date end) {
                    Date random = Merchants.this.getRandomDate(start);
                    addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                    random = Merchants.this.getRandomDate(start);
                    addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMeterGaugeModels() {
        setUnassignedMeter(assigned());
        setAssignedMeter(unassigned());
    }

    public String login() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }

            setUser((TbUser) getEm().createQuery("SELECT t FROM TbUser t WHERE t.userName = " + getUsername()));

            String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String Small_chars = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            // String symbols = "!@#$%^&*_=+-/.?<>)";
            String values = Capital_chars + Small_chars
                    + numbers;
            Random rndm_method = new Random();
            char[] password = new char[5];
            for (int i = 0; i < 5; i++) {
                password[i] = values.charAt(rndm_method.nextInt(values.length()));
            }

            getUtx().begin();
            getAudit().setAction("Retrieved OTP");
            getAudit().setCreatedby(user.getId());
            getAudit().setTimer(new java.util.Date());
            user.setOtp(password.toString());
            getEm().merge(user);
            getEm().persist(getAudit());
            getUtx().commit();

            send(user);
            return "otp.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            e.printStackTrace();
        }
        return null;
    }

    public void sendcredentials(TbUser args) {
        final String username = "taajiprs@gmail.com";
        final String password = "taaj1234";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("taajiprs@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(args.getEmail())
            );
            message.setSubject("WELCOME TO TAAJ IPRS SEARCH");
            message.setText("Dear " + args.getFirstName() + " " + args.getLastName()
                    + "\n\nPlease Login to http://197.157.228.150:8080/iprs using the password and username sent below \n password : 123456\n username  : " + args.getEmail() + "\n\nKind Regards\n"
                    + "Taaj Money Transfer");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String logout() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
            httpSession.invalidate();
            return "homePage.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            e.printStackTrace();
        }
        return null;
    }

    public void send(TbUser args) {
        final String username = "taajiprs@gmail.com";
        final String password = "taaj1234";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("taajiprs@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(args.getEmail())
            );
            message.setSubject("TAAJ IPRS ONE TIME PASSWORD");
            message.setText("Dear " + args.getFirstName() + " " + args.getLastName()
                    + "\n\nPlease Login to http://197.157.228.150:8080/iprs using the One time password sent below \n" + args.getOtp() + "\n\nKind Regards\n"
                    + "Taaj Money Transfer");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String confirmOtp() {
        try {
            setUser((TbUser) getEm().createQuery("select a from TbUser a where a.otp = '" + getOtp() + "'").getSingleResult());

            if (getUser().getStatus() == 1) {
                return "changePassword.xhtml?faces-redirect=true";
            } else {

                getUtx().begin();

                getAudit().setAction("verified OTP");
                getAudit().setCreatedby(getUser().getId());
                getAudit().setTimer(new java.util.Date());

                getEm().persist(getAudit());
                getUtx().commit();
                return "iprs.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            e.printStackTrace();
            return "homePage.xhtml?faces-redirect=true";
        }
    }

    public void upoadMerchants(FileUploadEvent event) {
        if (event.getFile() != null) {
            try {

                FacesContext fCtx = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
                String rootDirectory = session.getServletContext().getRealPath("/");
                Path path2 = Paths.get(rootDirectory + "uploads");

                String prefix = FilenameUtils.getBaseName(event.getFile().getFileName());
                String suffix = FilenameUtils.getExtension(event.getFile().getFileName());
                File save = File.createTempFile(prefix + "-", "." + suffix, path2.toFile());
                Files.write(save.toPath(), event.getFile().getContents(), new OpenOption[0]);
                Path path = save.toPath();
                String pathwithslashes = path.toString();
                System.out.println("the path is +++++++++++++++=" + pathwithslashes.replace("\\", "/"));
                utx.begin();
                em.createNativeQuery("LOAD DATA LOCAL INFILE '" + pathwithslashes.replace("\\", "/") + "' INTO TABLE  users FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n'").executeUpdate();
                utx.commit();
                FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception ex) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public String changePassword() {
        try {
            if (!newPassword.equals(confirmPword)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Passwords dont match");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
            } else {

                setUser((TbUser) getEm().createQuery("SELECT t FROM TbUser t WHERE t.userName = " + getUsername()).getSingleResult());
                getUser().setPasswordHash(encryptingPass(getNewPassword()));

                getUtx().begin();
                getUser().setCreatedOn(new java.util.Date());
                getAudit().setAction("changed password");
                getAudit().setCreatedby(getUser().getId());
                getAudit().setTimer(new java.util.Date());

                getEm().persist(getAudit());
                getEm().merge(getUser());
                getUtx().commit();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully changed your password");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
            }
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "User credentials dont exist in the database");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String resetPassword() {

        try {

            getUser().setPasswordHash(encryptingPass("123456"));
            getUtx().begin();
            getUser().setCreatedOn(new java.util.Date());

            getAudit().setAction("changed password");
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().merge(getUser());
            getUtx().commit();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully reset password to  " + getUser().getFirstName() + "'s password");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

//user
    public String createUser() {

        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }

            getUtx().begin();
            getUser().setCreatedOn(new java.util.Date());
            getUser().setPasswordHash(encryptingPass("123456"));
            getUser().setUserName(getUser().getEmail());

            getAudit().setAction("created User");
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().persist(getUser());
            getUtx().commit();
            sendcredentials(getUser());

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully created " + getUser().getFirstName());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String editUser() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            getEm().find(TbUser.class, getUser().getId());
            getUtx().begin();

            getAudit().setAction("Updated a User");
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().merge(getUser());
            getUtx().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteUser() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            user = getEm().find(TbUser.class, user.getId());

            getUtx().begin();

            getAudit().setAction("deleted a User " + user.getFirstName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().remove(user);
            getUtx().commit();

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();

        }
        return "empTransactions.xhtml";
    }

    //merchants 
    public String createMerchant() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }

            getUtx().begin();
            getAudit().setAction("created merchant " + merchants.getName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());
            getEm().persist(getAudit());
            getEm().persist(merchants);
            getUtx().commit();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully created " + merchants.getName());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String editMerchant() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            getEm().find(TbUmimerchants.class, merchants.getName());
            getUtx().begin();

            getAudit().setAction("Updated a merchant " + merchants.getName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().merge(merchants);
            getUtx().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteMerchant() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            merchants = getEm().find(TbUmimerchants.class, merchants.getName());

            getUtx().begin();

            getAudit().setAction("deleted a User " + user.getFirstName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().remove(merchants);
            getUtx().commit();

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();

        }
        return "empTransactions.xhtml";
    }

    //branches 
    public String createBranch() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }

            getUtx().begin();
            getAudit().setAction("created branch " + branch.getBranchName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());
            getEm().persist(getAudit());
            getEm().persist(branch);
            getUtx().commit();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully created " + branch.getBranchName());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String editBranch() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            getEm().find(TbBranch.class, getBranch().getId());
            getUtx().begin();

            getAudit().setAction("Updated a branch " + getBranch().getBranchName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().merge(branch);
            getUtx().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteBranch() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            branch = getEm().find(TbBranch.class, branch.getId());
            getUtx().begin();
            getAudit().setAction("deleted a branch " + branch.getBranchName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().remove(branch);
            getUtx().commit();

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();

        }
        return null;
    }

    //Payments 
    public String createPayment() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }

            getUtx().begin();
            getAudit().setAction("created payments " + payments.getPaymentData());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());
            getEm().persist(getAudit());
            getEm().persist(payments);
            getUtx().commit();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully created " + payments.getPaymentData());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String editPayments() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            getEm().find(TbUmipayments.class, payments.getIdUmipayments());
            getUtx().begin();

            getAudit().setAction("Updated a payment " + payments.getPaymentData());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().merge(payments);
            getUtx().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deletePayments() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            payments = getEm().find(TbUmipayments.class, payments.getIdUmipayments());
            getUtx().begin();
            getAudit().setAction("deleted a UMI payment " + payments.getPaymentData());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().remove(payments);
            getUtx().commit();

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();

        }
        return null;
    }

    //status 
    public String createStatus() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }

            getUtx().begin();
            getAudit().setAction("created status " + getStatus().getName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());
            getEm().persist(getAudit());
            getEm().persist(getStatus());
            getUtx().commit();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully created " + getStatus().getName());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String editStatus() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            getEm().find(Status.class, getStatus().getIdstatus());
            getUtx().begin();

            getAudit().setAction("Updated a status " + getStatus().getName());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().merge(getStatus());
            getUtx().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteStatus() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            payments = getEm().find(TbUmipayments.class, payments.getIdUmipayments());
            getUtx().begin();
            getAudit().setAction("deleted a UMI payment " + payments.getPaymentData());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().remove(payments);
            getUtx().commit();

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    //Transactions 
    public String createTransactions() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }

            getUtx().begin();
            getAudit().setAction("created transaction " + transactions.getReferenceNumber());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());
            getEm().persist(getAudit());
            getEm().persist(transactions);
            getUtx().commit();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!success!", "You have successfully created " + transactions.getReferenceNumber());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String editTransactions() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            getEm().find(TbTransaction.class, transactions.getId());
            getUtx().begin();

            getAudit().setAction("Updated a status " + transactions.getReferenceNumber());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().merge(transactions);
            getUtx().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteTransactions() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please login to the system");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "homePage.xhtml";
            }
            transactions = getEm().find(TbTransaction.class, transactions.getId());
            getUtx().begin();
            getAudit().setAction("deleted a transaction " + transactions.getReferenceNumber());
            getAudit().setCreatedby(getUser().getId());
            getAudit().setTimer(new java.util.Date());

            getEm().persist(getAudit());
            getEm().remove(transactions);
            getUtx().commit();

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            ex.printStackTrace();
        }
        return null;
    }

    public String encryptingPass(String args) {
        String str = new String(Base64.encodeBase64(args.getBytes()));
        return str;
    }

    public String decryptingPass(String args) {
        String str = new String(Base64.decodeBase64(args.getBytes()));
        return str;
    }

    private MeterGaugeChartModel assigned() {
        List<Number> intervals = new ArrayList() {
        };
        for (int i = 0; i < 200; i++) {
            intervals.add(i + 20);
        }
        return new MeterGaugeChartModel(Integer.valueOf(130), intervals);
    }

    private MeterGaugeChartModel unassigned() {
        List<Number> intervals = new ArrayList() {
        };
        for (int i = 0; i < 200; i++) {
            intervals.add(i + 20);
        }
        return new MeterGaugeChartModel(Integer.valueOf(130), intervals);
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(5, (int) (Math.random() * 30.0D) + 1);

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), 1, calendar.get(5), 0, 0, 0);

        return calendar.getTime();
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(9, 1);
        t.set(5, t.get(5) - 1);
        t.set(10, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(9, 1);
        t.set(5, t.get(5) - 1);
        t.set(10, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(9, 1);
        t.set(10, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(5, t.get(5) + 2);
        t.set(9, 1);
        t.set(10, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(9, 1);
        t.set(10, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(9, 0);
        t.set(5, t.get(5) + 1);
        t.set(10, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(9, 0);
        t.set(5, t.get(5) + 1);
        t.set(10, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(9, 1);
        t.set(5, t.get(5) + 4);
        t.set(10, 3);

        return t.getTime();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MeterGaugeChartModel getAssignedMeter() {
        return assignedMeter;
    }

    public void setAssignedMeter(MeterGaugeChartModel assignedMeter) {
        this.assignedMeter = assignedMeter;
    }

    public MeterGaugeChartModel getUnassignedMeter() {
        return unassignedMeter;
    }

    public void setUnassignedMeter(MeterGaugeChartModel unassignedMeter) {
        this.unassignedMeter = unassignedMeter;
    }

    /**
     * @return the lazyEventModel
     */
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    /**
     * @param lazyEventModel the lazyEventModel to set
     */
    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the utx
     */
    public UserTransaction getUtx() {
        return utx;
    }

    /**
     * @param utx the utx to set
     */
    public void setUtx(UserTransaction utx) {
        this.utx = utx;
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {

        this.eventModel = eventModel;
    }

    public String sendOTP(String phoneNumber) {
        return "";
    }

    /**
     * @return the user
     */
    public TbUser getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(TbUser user) {
        this.user = user;
    }

    /**
     * @return the userList
     */
    public List<TbUser> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<TbUser> userList) {
        this.userList = userList;
    }

    /**
     * @param audit the audit to set
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    /**
     * @return the merchants
     */
    public TbUmimerchants getMerchants() {
        return merchants;
    }

    /**
     * @param merchants the merchants to set
     */
    public void setMerchants(TbUmimerchants merchants) {
        this.merchants = merchants;
    }

    /**
     * @return the merchantsList
     */
    public List<TbUmimerchants> getMerchantsList() {
        merchantsList = em.createQuery("select t from TbUmimerchants t").getResultList();
        return merchantsList;
    }

    /**
     * @param merchantsList the merchantsList to set
     */
    public void setMerchantsList(List<TbUmimerchants> merchantsList) {
        this.merchantsList = merchantsList;
    }

    /**
     * @return the branch
     */
    public TbBranch getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(TbBranch branch) {
        this.branch = branch;
    }

    /**
     * @return the branchList
     */
    public List<TbBranch> getBranchList() {
        branchList = em.createQuery("select t from TbBranch t").getResultList();
        return branchList;
    }

    /**
     * @param branchList the branchList to set
     */
    public void setBranchList(List<TbBranch> branchList) {
        this.branchList = branchList;
    }

    /**
     * @return the payments
     */
    public TbUmipayments getPayments() {
        return payments;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(TbUmipayments payments) {
        this.payments = payments;
    }

    /**
     * @return the paymentsList
     */
    public List<TbUmipayments> getPaymentsList() {
        paymentsList = em.createQuery("select t FROM TbUmipayments t").getResultList();
        return paymentsList;
    }

    /**
     * @param paymentsList the paymentsList to set
     */
    public void setPaymentsList(List<TbUmipayments> paymentsList) {
        this.paymentsList = paymentsList;
    }

    /**
     * @return the transactions
     */
    public TbTransaction getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(TbTransaction transactions) {
        this.transactions = transactions;
    }

    /**
     * @return the transactionsList
     */
    public List<TbTransaction> getTransactionsList() {
        transactionsList = em.createQuery("select t from TbTransaction t").getResultList();
        return transactionsList;
    }

    /**
     * @param transactionsList the transactionsList to set
     */
    public void setTransactionsList(List<TbTransaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    /**
     * @return the selectedResponsibilities
     */
    public String[] getSelectedResponsibilities() {
        return selectedResponsibilities;
    }

    /**
     * @param selectedResponsibilities the selectedResponsibilities to set
     */
    public void setSelectedResponsibilities(String[] selectedResponsibilities) {
        this.selectedResponsibilities = selectedResponsibilities;
    }

    /**
     * @return the htmlDataTable
     */
    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    /**
     * @param htmlDataTable the htmlDataTable to set
     */
    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }

    /**
     * @return the citizenShip
     */
    public String getCitizenShip() {
        return citizenShip;
    }

    /**
     * @param citizenShip the citizenShip to set
     */
    public void setCitizenShip(String citizenShip) {
        this.citizenShip = citizenShip;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confirmPword
     */
    public String getConfirmPword() {
        return confirmPword;
    }

    /**
     * @param confirmPword the confirmPword to set
     */
    public void setConfirmPword(String confirmPword) {
        this.confirmPword = confirmPword;
    }

    /**
     * @return the group
     */
    public boolean isGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(boolean group) {
        this.group = group;
    }

    /**
     * @return the audittrails
     */
    public boolean isAudittrails() {
        return audittrails;
    }

    /**
     * @param audittrails the audittrails to set
     */
    public void setAudittrails(boolean audittrails) {
        this.audittrails = audittrails;
    }

    /**
     * @return the iprsTransactionsView
     */
    public boolean isIprsTransactionsView() {
        return iprsTransactionsView;
    }

    /**
     * @param iprsTransactionsView the iprsTransactionsView to set
     */
    public void setIprsTransactionsView(boolean iprsTransactionsView) {
        this.iprsTransactionsView = iprsTransactionsView;
    }

    /**
     * @return the iprsQuery
     */
    public boolean isIprsQuery() {
        return iprsQuery;
    }

    /**
     * @param iprsQuery the iprsQuery to set
     */
    public void setIprsQuery(boolean iprsQuery) {
        this.iprsQuery = iprsQuery;
    }

    /**
     * @return the rolesView
     */
    public boolean isRolesView() {
        return rolesView;
    }

    /**
     * @param rolesView the rolesView to set
     */
    public void setRolesView(boolean rolesView) {
        this.rolesView = rolesView;
    }

    /**
     * @return the userCreateView
     */
    public boolean isUserCreateView() {
        return userCreateView;
    }

    /**
     * @param userCreateView the userCreateView to set
     */
    public void setUserCreateView(boolean userCreateView) {
        this.userCreateView = userCreateView;
    }

    /**
     * @return the otp
     */
    public String getOtp() {
        return otp;
    }

    /**
     * @param otp the otp to set
     */
    public void setOtp(String otp) {
        this.otp = otp;
    }

    /**
     * @return the audit
     */
    public Audit getAudit() {
        return audit;
    }

    /**
     * @return the auditList
     */
    public List<Audit> getAuditList() {
        auditList = em.createQuery("select a from Audit a").getResultList();
        return auditList;
    }

    /**
     * @param auditList the auditList to set
     */
    public void setAuditList(List<Audit> auditList) {
        this.auditList = auditList;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the statusList
     */
    public List<Status> getStatusList() {
        statusList = em.createQuery("select s from Status s").getResultList();
        return statusList;
    }

    /**
     * @param statusList the statusList to set
     */
    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

}
