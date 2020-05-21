/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.amon.db.*;
import com.amon.mne.ParameterStringBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartSeries;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author amon.sabul
 */
@javax.faces.bean.ManagedBean(name = "maaif")
@SessionScoped
public class maaif implements Serializable {

    Logger logger = Logger.getLogger("errorLog");
    @PersistenceContext(unitName = "maaifPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private String[] responsibilities;
    private Boolean remember = false;
    private Agrodealerproducts agrodealerProduct = new Agrodealerproducts();
    private List<Agrodealerproducts> agrodealerProductList = new ArrayList<Agrodealerproducts>();
    private List<Agrodealerproducts> agrodealerProductListUnchecked = new ArrayList<Agrodealerproducts>();
    private List<Agrodealerproducts> blankagrodealerProductList = new ArrayList<Agrodealerproducts>();
    private List<Agrodealerproducts> varietiesList = new ArrayList<Agrodealerproducts>();
    private List<Agrodealerproducts> availableTypesList = new ArrayList<Agrodealerproducts>();
    private List<Agrodealerproducts> packageList = new ArrayList<Agrodealerproducts>();
    private Ewallet ewallet = new Ewallet();
    private List<Ewallet> ewalletList = new ArrayList<Ewallet>();
    private Agrodealerproductunits agrodealerproductunits = new Agrodealerproductunits();
    private List<Agrodealerproductunits> AgrodealerproductunitsList = new ArrayList<Agrodealerproductunits>();
    private Agrodealers agrodealers = new Agrodealers();
    private List<Agrodealers> agrodealersList = new ArrayList<Agrodealers>();
    private Audit audit = new Audit();
    private List<Audit> auditList = new ArrayList<Audit>();
    private List<Audit> blankauditList = new ArrayList<Audit>();
    private List<Audit> purchaseAudit = new ArrayList<Audit>();
    private Banks banks = new Banks();
    private List<Banks> banksList = new ArrayList<Banks>();
    private Govtpayments govtpayments = new Govtpayments();
    private List<Govtpayments> GovtpaymentsList = new ArrayList<Govtpayments>();
    private Regions regions = new Regions();
    private List<Regions> regionsList = new ArrayList<Regions>();
    private Agrodealerproducttypes types = new Agrodealerproducttypes();
    private List<Agrodealerproducttypes> productTypesList = new ArrayList<Agrodealerproducttypes>();
    private Orders orders = new Orders();
    private List<Orders> orderslist = new ArrayList<Orders>();
    private Distributionchannel channels = new Distributionchannel();
    private List<Distributionchannel> channelList = new ArrayList<Distributionchannel>();
    private Cropproducts cropproduct = new Cropproducts();
    private List<Cropproducts> cropproductList = new ArrayList<Cropproducts>();
    private List<Distributionchannel> unsupervisedchannelList = new ArrayList<Distributionchannel>();
    private Status status = new Status();
    private List<Status> statusList = new ArrayList<Status>();
    private User user = new User();
    private Unbs unbs = new Unbs();
    private Nira nira = new Nira();
    private User users = new User();
    private User farmers = new User();
    private List<User> userList = new ArrayList<User>();
    private Farmergroups farmergroups = new Farmergroups();
    private List<Farmergroups> farmergroupsList = new ArrayList<Farmergroups>();
    private List<Regionmodel> Regionmodel = new ArrayList<Regionmodel>();
    private List<Agrodealersperregion> dealersPerregion = new ArrayList<Agrodealersperregion>();
    private List<Cropmodel> Cropmodel = new ArrayList<Cropmodel>();
    private List<User> farmersList = new ArrayList<User>();
    private List<User> blankfarmersList = new ArrayList<User>();
    private List<User> blankuserList = new ArrayList<User>();
    private Usergroup group1 = new Usergroup();
    private List<Usergroup> group1List = new ArrayList<Usergroup>();
    private Userpurchase userpurchase = new Userpurchase();
    private List<Userpurchase> userpurchaseList = new ArrayList<Userpurchase>();
    private List<Userpurchase> cartList = new ArrayList<Userpurchase>();
    private List<Userpurchase> generalCartList = new ArrayList<Userpurchase>();
    private Double totalPrice = new Double(0);
    private Double subsidizedvalue = new Double(0);
    private Double farmerPays = new Double(0);
    private List<Userpurchase> agrodealerpurchaseList = new ArrayList<Userpurchase>();
    private final String USER_AGENT = "Mozilla/5.0";
    private String username = new String();
    private String password = new String();
    private Long agrodealersCount;
    private Long productsCount;
    private Integer quantity = 1;
    private Long userCount;
    private Long malefarmers;
    private Long femalefarmers;

    private Boolean agrodealer = false;
    private Boolean cvv = false;
    private Boolean accountNumber = false;
    private Boolean phoneNumber = false;
    private Boolean accpassword = false;
    private Boolean admin = false;
    private Boolean farmer = false;
    private Boolean government = false;
    private Boolean UBA = false;

    private UploadedFile file;

    /**
     * Creates a new instance of mne
     */
    public maaif() {
    }

    @PostConstruct
    public void init() {
        try {
            createAgroDealersperregion();
            createDistrictModel();
            createCropModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String orientation = "horizontal";

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    private BarChartModel regionModel;
    private BarChartModel cropModel;
    private BarChartModel agrodealersperRegion;

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getAttributes().clear();
        return "/index.xhtml?faces-redirect=true";
    }

    public String loginPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getAttributes().clear();
        return "/index2.xhtml?faces-redirect=true";
    }

    public String login() {
        try {
            setQuantity((Integer) 1);
            Usergroup g;
            setUser((User) getEm().createQuery("select u from User u where u.username = '" + getUsername() + "' and u.pword = '" + getPassword() + "'").getSingleResult());
            g = (Usergroup) getEm().createQuery("select g from Usergroup g where g.name = '" + getUser().getGroupID().getName() + "'").getSingleResult();
            if (g.getName().equalsIgnoreCase("Admin")) {
                setAdmin(true);
                setAgrodealer(false);
                setGovernment(false);
                setUBA(false);
                setFarmer(false);
                return "users.xhtml?faces-redirect=true";

            }
            if (g.getName().equalsIgnoreCase("Farmer")) {
                setFarmer(true);
                setAdmin(false);
                setUBA(false);
                setAgrodealer(false);
                setGovernment(false);
                return "welcome.xhtml?faces-redirect=true";
            }
            if (g.getName().equalsIgnoreCase("Importer/manufacturer")) {
                setAgrodealer(true);
                setAdmin(false);
                setGovernment(false);
                setUBA(false);
                setFarmer(false);
                return "agrodealersproducts.xhtml?faces-redirect=true";
            }
            if (g.getName().equalsIgnoreCase("Merchants/Stockiest")) {
                setAgrodealer(true);
                setAdmin(false);
                setGovernment(false);
                setUBA(false);
                setFarmer(false);
                return "agrodealersproducts.xhtml?faces-redirect=true";
            }
            if (g.getName().equalsIgnoreCase("Government")) {
                setAdmin(false);
                setAgrodealer(false);
                setGovernment(true);
                setUBA(false);
                setFarmer(false);
                return "report.xhtml?faces-redirect=true";
            }

            if (g.getName().equalsIgnoreCase("UBA")) {
                setAdmin(false);
                setAgrodealer(false);
                setGovernment(false);
                setUBA(true);
                setFarmer(false);
                return "report.xhtml?faces-redirect=true";
            }

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please provide correct credentials");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    private boolean skip;

    public String onFlowProcess(FlowEvent event) {
        if (isSkip()) {
            setSkip(false);   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    private void createDistrictModel() {
        setRegionmodel((List<Regionmodel>) em.createQuery("select r from Regionmodel r").getResultList());
        setRegionModel(new BarChartModel());
        LineChartSeries Cohort = new LineChartSeries();
        Cohort.setFill(true);
        Cohort.setLabel("Number of farmers");

        for (Regionmodel med : getRegionmodel()) {
            Cohort.set(med.getRegion().getWard(), med.getFarmers());
        }
        getRegionModel().addSeries(Cohort);
        getRegionModel().setTitle("Farmers per ward");
        getRegionModel().setLegendPosition("ne");
        getRegionModel().setStacked(true);
        getRegionModel().setShowPointLabels(true);
        Axis xAxis = new CategoryAxis("Wards");
        getRegionModel().getAxes().put(AxisType.X, xAxis);
        Axis yAxis = getRegionModel().getAxis(AxisType.Y);
        yAxis.setLabel("Number of Farmers");
        yAxis.setMin(0);
    }

    private void createAgroDealersperregion() {
        setDealersPerregion((List<Agrodealersperregion>) em.createQuery("select a from Agrodealersperregion a").getResultList());
        setAgrodealersperRegion(new BarChartModel());
        LineChartSeries Cohort = new LineChartSeries();
        Cohort.setFill(true);
        Cohort.setLabel("Number of farmers");

        for (Agrodealersperregion med : getDealersPerregion()) {
            Cohort.set(med.getRegionid().getWard(), med.getAgrodealers());
        }
        getAgrodealersperRegion().addSeries(Cohort);
        getAgrodealersperRegion().setTitle("Dealers Per Ward");
        getAgrodealersperRegion().setLegendPosition("ne");
        getAgrodealersperRegion().setStacked(true);
        getAgrodealersperRegion().setShowPointLabels(true);
        Axis xAxis = new CategoryAxis("Wards");
        getAgrodealersperRegion().getAxes().put(AxisType.X, xAxis);
        Axis yAxis = getAgrodealersperRegion().getAxis(AxisType.Y);
        yAxis.setLabel("Number of Agro dealers");
        yAxis.setMin(0);
    }

    private void createCropModel() {
        setCropmodel((List<Cropmodel>) em.createQuery("select c from Cropmodel c").getResultList());
        setCropModel(new BarChartModel());
        LineChartSeries Cohort = new LineChartSeries();
        Cohort.setFill(true);
        Cohort.setLabel("Number of farmers");

        for (Cropmodel med : getCropmodel()) {
            Cohort.set(med.getCropgrown(), med.getFarmers());
        }
        getCropModel().addSeries(Cohort);
        getCropModel().setTitle("Report on farmers per crop");
        getCropModel().setLegendPosition("ne");
        getCropModel().setStacked(true);
        getCropModel().setShowPointLabels(true);
        Axis xAxis = new CategoryAxis("Crops");
        getCropModel().getAxes().put(AxisType.X, xAxis);
        Axis yAxis = getCropModel().getAxis(AxisType.Y);
        yAxis.setLabel("Number of farmers");
        yAxis.setMin(0);

    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            try {
                if (agrodealerProduct.getCreatedby().toString().isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "You have attempted to upload an invalid product please visit UNBS to obtain further INformation "));
                } else {
                    FacesContext fCtx = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
                    String rootDirectory = session.getServletContext().getRealPath("/");
                    Path path2 = Paths.get(rootDirectory + "uploads");

                    String prefix = FilenameUtils.getBaseName(event.getFile().getFileName());
                    String suffix = FilenameUtils.getExtension(event.getFile().getFileName());
                    File save = File.createTempFile(prefix + "-", "." + suffix, path2.toFile());

                    Files.write(save.toPath(), event.getFile().getContents(), new OpenOption[0]);
                    Path path = save.toPath();

                    agrodealerProduct.setPhotourl("uploads\\" + path.getFileName());

                    System.out.println(agrodealerProduct.getCreatedby());
                    System.out.println(agrodealerProduct.getCreatedon());
                    System.out.println(agrodealerProduct.getAgrodealerproductunit().getUnitname());
                    System.out.println(agrodealerProduct.getDealerid().getCompanyname());
                    System.out.println(agrodealerProduct.getDescription());
                    System.out.println(agrodealerProduct.getPrice());
                    System.out.println(agrodealerProduct.getProductname());
                    System.out.println(agrodealerProduct.getQuantitypresent());
                    System.out.println(agrodealerProduct.getStatus().getName());
                    getUtx().begin();

                    getEm().persist(getAudit());
                    getEm().persist(agrodealerProduct);
                    getUtx().commit();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Image uploaded successfully"));
                }

            } catch (Exception ex) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not upload the product photo please try again"));
                logger.info(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void handleFileUpload2(FileUploadEvent event) {
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
                em.createNativeQuery("LOAD DATA LOCAL INFILE '" + pathwithslashes.replace("\\", "/") + "' INTO TABLE  govtpayments FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n'").executeUpdate();
                utx.commit();

                FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception ex) {
                logger.info(ex.getMessage());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void handleOrderChannels(FileUploadEvent event) {
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
                em.createNativeQuery("LOAD DATA LOCAL INFILE '" + pathwithslashes.replace("\\", "/") + "' INTO TABLE  orders FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n'").executeUpdate();
                utx.commit();
                FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception ex) {
                logger.info(ex.getMessage());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void uploadFarmers(FileUploadEvent event) {
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
                logger.info(ex.getMessage());
            }
        }
    }

    public void upoadAgrodealers(FileUploadEvent event) {
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
                logger.info(ex.getMessage());
            }
        }
    }

    public void upoadProducts(FileUploadEvent event) {
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
                em.createNativeQuery("LOAD DATA LOCAL INFILE '" + pathwithslashes.replace("\\", "/") + "' INTO TABLE agrodealerproducts FIELDS TERMINATED BY ',' LINES TERMINATED BY 'DETAILS'").executeUpdate();
                utx.commit();
                FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception ex) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
                logger.info(ex.getMessage());
            }
        }
    }

    public String createProduct() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();
            if (UNBS(agrodealerProduct.getUbsid().toString(), agrodealerProduct.getProductSerial()).equalsIgnoreCase("000")) {
                agrodealerProduct.setCreatedby(user);
                agrodealerProduct.setCreatedon(new java.util.Date());
                agrodealerProduct.setStatus(new Status(5));
                getAudit().setAction("saved product " + agrodealerProduct.getProductname());
                getAudit().setCreatedby(getUser());
                getAudit().setTimer(new Date());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", agrodealerProduct.getProductname() + " is certified by UNBS code " + randomUUIDString + " upload photo to complete product setup"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", agrodealerProduct.getProductname() + " is not certified by UNBS. Visit UNBS to receive further communication on the same"));
            }

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }

        return null;
    }

    public void updateQuantityPurchased() {
        agrodealerProduct.setQtypurchased(agrodealerProduct.getQtypurchased());
    }

    public String topup() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Ewallet ew = (Ewallet) em.createQuery("select e from Ewallet e where e.userid.idusers=" + user.getIdusers() + "").getSingleResult();
            ew.setBalance(ewallet.getBalance() + ew.getBalance());
            utx.begin();
            getAudit().setAction("Topup " + ewallet.getBalance());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            em.persist(audit);
            em.merge(ew);
            utx.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "You have successfully topped up your wallet with " + ewallet.getBalance()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return null;
    }

    public String registerFarmer() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            System.out.println(farmers.getStaffID().toString());

            if (NIRA(farmers.getStaffID().toString()).equalsIgnoreCase("000")) {
                getUtx().begin();
                getAudit().setAction("saved user " + getUsers().getUsername());
                getAudit().setCreatedby(getUser());
                getAudit().setTimer(new Date());
                farmers.setGroupID(new Usergroup(2));
                farmers.setCreatedAt(new java.util.Date());
                farmers.setCreatedBy(user);
                farmers.setStatusID(new Status(2));
                getEm().persist(getAudit());
                getEm().persist(farmers);
                getUtx().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", farmers.getName() + " saved successfully."));
                setUsers(new User());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", farmers.getName() + " is not registered at NIRA. please register and try again"));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return null;
    }

    public void populatePackage() {
        try {
            quantity = new Integer(0);
            getPackageList();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wiz').next()");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    public String addCart() {
        System.out.println("The products name is " + agrodealerProduct.getProductname());
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Agrodealerproducts agroprods = getEm().find(Agrodealerproducts.class, getAgrodealerProduct().getIdagrodealerproducts());
            setQuantity(quantity);
            agroprods.setCreatedby(user);
            agroprods.setCreatedon(new java.util.Date());
            agroprods.setDealerid(agrodealerProduct.getDealerid());
            agroprods.setDescription(agrodealerProduct.getDescription());
            agroprods.setAgrodealerproductunit(agrodealerProduct.getAgrodealerproductunit());
            agroprods.setOrderid(Integer.SIZE);
            agroprods.setPrice(agrodealerProduct.getPrice());
            agroprods.setProductname(agrodealerProduct.getProductname());
            agroprods.setQuantitypresent(agrodealerProduct.getQuantitypresent());

            Ewallet ewal = (Ewallet) em.createQuery("select e from Ewallet e where e.userid.idusers = " + user.getIdusers() + "").getSingleResult();
            Double balance = ewal.getBalance() - (agrodealerProduct.getPrice() - 67 / 100 * agrodealerProduct.getPrice()) * quantity;
            if ((agrodealerProduct.getPrice() - 67 / 100 * agrodealerProduct.getPrice()) * quantity > ewal.getBalance()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Check Balance!", "Dear " + user.getName() + " Sorry you do not have enough amount to purchase " + userpurchase.getAgrodealerproductid().getProductname() + "your current balance is " + ewal.getBalance() + " Topup your wallet to continue purchasing"));
            }
            if (balance <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Dear " + user.getName() + " Sorry you do not have enough balance to purchase " + userpurchase.getAgrodealerproductid().getProductname() + " Topup your wallet to continue purchasing"));
            } else {
                ewal.setBalance(balance);
                userpurchase.setCreatedby(getUser());
                userpurchase.setCreatedon(new java.util.Date());
                userpurchase.setStatusID(new Status(6));
                userpurchase.setUserid(getUser());
                userpurchase.setPrice((agrodealerProduct.getPrice() - 67 / 100 * agrodealerProduct.getPrice()) * quantity);
                userpurchase.setAgrodealerproductid(agrodealerProduct);
                userpurchase.setQuantity(getQuantity());
                getUtx().begin();
                getAudit().setAction("purchased " + userpurchase.getAgrodealerproductid().getProductname() + " at " + new java.util.Date() + " for " + userpurchase.getPrice());
                getAudit().setCreatedby(getUser());
                getAudit().setPurchase("purchase");
                getAudit().setTimer(new Date());
                getEm().persist(getAudit());
                getEm().persist(getUserpurchase());
                getUtx().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "You have added " + getUserpurchase().getAgrodealerproductid().getProductname() + "  successfully. "
                        + "Go ahead and pay for the commodity"));
                quantity = new Integer(0);
                return null;
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage()));
            ex.printStackTrace();
        }
        return null;
    }

    public void changed(final AjaxBehaviorEvent event) {

        System.out.println(userpurchase.getPaymentmode().getBank());
        if (userpurchase.getPaymentmode().getBank() == 1) {
            cvv = true;
            accountNumber = true;
            accpassword = true;
            phoneNumber = false;

        } else {
            phoneNumber = true;
            cvv = false;
            accountNumber = false;
            accpassword = false;
        }
    }

    public String payout() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }

            cartList = em.createQuery("select u from Userpurchase u where u.statusID.name = 'cart' and u.userid.name ='" + user.getName() + "'").getResultList();
            for (Userpurchase up : cartList) {

                getUtx().begin();
                getAudit().setAction("payed for " + up.getAgrodealerproductid().getProductname() + " at " + new java.util.Date() + " for " + up.getPrice());
                getAudit().setCreatedby(getUser());
                getAudit().setPurchase("purchase");
                getAudit().setTimer(new Date());
                up.setStatusID(new Status(4));
                up.setPaymentmode(userpurchase.getPaymentmode());
                getEm().persist(getAudit());
                getEm().merge(up);
                getUtx().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "You have purchased " + up.getAgrodealerproductid().getProductname() + "  successfully. "
                        + " scan the commodity's bar code to verify legibility once received"));
            }

            return null;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));

            ex.printStackTrace();
        }
        return null;

    }

    public String UNBS(String productID, String product) {
        try {
            Unbs unbs = (Unbs) em.createQuery("select u from Unbs u where u.productID ='" + productID + "' and u.productSerial = '" + product + "'").getSingleResult();
            return "000";
        } catch (Exception ex) {
            return "091";
        }
    }

    public String acknowledge() {
        this.cartList = cartList;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "!Congratulations!", "You have successfully acknowledged the items in your shopping cart. Please go ahead and pay for them using any modes stated below"));
        return null;
    }

    public String NIRA(String idNumber) {
        System.out.println(idNumber);
        try {
            Nira unbs = (Nira) em.createQuery("select n from Nira n where n.idNumber ='" + idNumber + "'").getSingleResult();
            return "000";
        } catch (Exception ex) {
            return "091";
        }
    }

    public String createUser() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }

            if (NIRA(users.getStaffID().toString()).equalsIgnoreCase("000")) {
                getUtx().begin();
                getAudit().setAction("saved user " + getUsers().getUsername());
                getAudit().setCreatedby(getUser());
                getAudit().setTimer(new Date());
                users.setCreatedAt(new java.util.Date());
                users.setCreatedBy(user);

                getEm().persist(getAudit());
                getEm().persist(getUsers());
                getUtx().commit();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getUsers().getName() + " saved successfully."));
                setUsers(new User());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", getUsers().getName() + " is not registered at NIRA. please register and try again"));
            }

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return null;
    }

    public String updateUser() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            User user = getEm().find(User.class, farmers.getIdusers());
            user.setName(farmers.getName());
            user.setPhone(farmers.getPhone());
            user.setStaffID(farmers.getStaffID());
            user.setGroupID(farmers.getGroupID());
            user.setDisability(farmers.getDisability());
            user.setRegion(farmers.getRegion());
            user.setCropgrown(farmers.getCropgrown());
            user.setEmail(farmers.getEmail());
            user.setUsername(farmers.getUsername());
            user.setFarmergroup(farmers.getFarmergroup());
            getUtx().begin();
            getAudit().setAction("updated user " + users.getIdusers());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(user);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", users.getName() + " Updated successfully."));
            users = new User();
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a user."));
        }

        return null;
    }

    public String deleteUser(User user) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted user");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            User toBeRemoved = (User) getEm().merge(users);
            getEm().remove(toBeRemoved);
            getUtx().commit();
            users = new User();
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User deleted", "User deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
        }

        return null;
    }

    public String createAgrodealerproducttypes() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }

            getUtx().begin();
            getAudit().setAction("saved user " + types.getProductname());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            types.setCreatedon(new java.util.Date());
            types.setCreatedby(user);
            types.setStatus(new Status(5));
            getEm().persist(getAudit());
            getEm().persist(types);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", types.getProductname() + " saved successfully."));
            setTypes(new Agrodealerproducttypes());

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return null;
    }

    public String updateAgrodealerproducttypes() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Agrodealerproducttypes user = getEm().find(Agrodealerproducttypes.class, getTypes().getIdagrodealerproducttypes());

            this.types = types;
            getUtx().begin();
            getAudit().setAction("updated user " + users.getIdusers());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(user);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", users.getName() + " Updated successfully."));
            setTypes(new Agrodealerproducttypes());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a user."));
        }

        return null;
    }

    public String deleteAgrodealerproducttypes(Agrodealerproducttypes user) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted user");
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Agrodealerproducttypes toBeRemoved = (Agrodealerproducttypes) getEm().merge(types);
            getEm().remove(toBeRemoved);
            getUtx().commit();
            setTypes(new Agrodealerproducttypes());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User deleted", "User deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
        }

        return null;
    }

    public String createFarmergroups() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }

            getUtx().begin();
            getAudit().setAction("saved user " + farmergroups.getName());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            farmergroups.setCreatedon(new java.util.Date());
            farmergroups.setCreatedby(user);
            farmergroups.setStatusID(new Status(5));
            getEm().persist(getAudit());
            getEm().persist(farmergroups);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", farmergroups.getName() + " saved successfully."));
            setFarmergroups(new Farmergroups());

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return null;
    }

    public String updateFarmergroups() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Farmergroups farmergroups = getEm().find(Farmergroups.class, getFarmergroups().getIdfarmergroups());

            this.farmergroups = farmergroups;
            getUtx().begin();
            getAudit().setAction("updated farmergroups " + farmergroups.getName());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(farmergroups);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", farmergroups.getName() + " Updated successfully."));
            setFarmergroups(new Farmergroups());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a farmer group."));
        }

        return null;
    }

    public String deleteFarmergroups(Farmergroups farmergroups) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted farmergroups");
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Farmergroups toBeRemoved = (Farmergroups) getEm().merge(farmergroups);
            getEm().remove(toBeRemoved);
            getUtx().commit();
            setFarmergroups(new Farmergroups());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "farmergroups deleted", "farmergroups deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("farmergroups", success);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("farmergroups", success);
        }

        return null;
    }

    public String updateAgrodealerProduct() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Agrodealerproducts agroprods = getEm().find(Agrodealerproducts.class, getAgrodealerProduct().getIdagrodealerproducts());

            agroprods.setCreatedby(user);
            agroprods.setCreatedon(new java.util.Date());
            agroprods.setDealerid(agrodealerProduct.getDealerid());
            agroprods.setDescription(agrodealerProduct.getDescription());
            agroprods.setAgrodealerproductunit(agrodealerProduct.getAgrodealerproductunit());
            agroprods.setOrderid(Integer.SIZE);
            agroprods.setPrice(agrodealerProduct.getPrice());
            agroprods.setProductname(agrodealerProduct.getProductname());
            agroprods.setQuantitypresent(agrodealerProduct.getQuantitypresent());
            agroprods.setVariety(agrodealerProduct.getVariety());
            agroprods.setProducttype(agrodealerProduct.getProducttype());
            agroprods.setProductpackage(agrodealerProduct.getProductpackage());

            getUtx().begin();
            getAudit().setAction("updated product " + agrodealerProduct.getProductname());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(agroprods);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", agrodealerProduct.getProductname() + " Updated successfully."));
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a product."));
        }
        return null;
    }

    public String deleteAgrodealerProduct() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Agrodealerproducts agrodealerProducts = (Agrodealerproducts) em.createQuery("select a from Agrodealerproducts a where a.idagrodealerproducts=" + agrodealerProduct.getIdagrodealerproducts() + "").getSingleResult();
            getUtx().begin();
            getAudit().setAction("Deleted product");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Agrodealerproducts toBeRemoved = (Agrodealerproducts) getEm().merge(agrodealerProducts);
            getEm().remove(toBeRemoved);
            getUtx().commit();
            agrodealerProducts = new Agrodealerproducts();
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_INFO, "product deleted", "product deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
        }
        agrodealerProduct = new Agrodealerproducts();
        return null;
    }

    public String createAgrodealerproductunits() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            status.setIdstatus(1);
            getUtx().begin();
            getAudit().setAction("saved product " + agrodealerproductunits.getUnitname());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            agrodealerproductunits.setCreatedby(user);
            agrodealerproductunits.setCreatedon(new java.util.Date());
            agrodealerproductunits.setStatusID(status);

            getEm().persist(getAudit());
            getEm().persist(agrodealerproductunits);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", agrodealerproductunits.getUnitname() + " saved successfully."));
            setAgrodealerproductunits(new Agrodealerproductunits());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }

        return null;
    }

    public String updateAgrodealerproductunits() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Agrodealerproductunits prod = getEm().find(Agrodealerproductunits.class, agrodealerproductunits.getIdagrodealerproductunits());
            prod = agrodealerproductunits;
            prod.setCreatedby(user);
            prod.setCreatedon(new java.util.Date());
            prod.setStatusID(new Status(1));

            getUtx().begin();
            getAudit().setAction("updated product " + agrodealerproductunits.getUnitname());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(prod);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", agrodealerproductunits.getUnitname() + " Updated successfully."));
            setAgrodealerproductunits(new Agrodealerproductunits());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a product."));
        }
        setAgrodealerproductunits(new Agrodealerproductunits());
        return null;
    }

    public String deleteAgrodealerproductunits(Agrodealerproductunits product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted product");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Agrodealerproductunits toBeRemoved = (Agrodealerproductunits) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "product deleted", "product deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
            product = new Agrodealerproductunits();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
        }

        return null;
    }

    public String createDistributionchannel() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Agrodealers agrodealer = (Agrodealers) em.createQuery("select a from Agrodealers a where a.ownerid.name = '" + user.getName() + "'").getSingleResult();
            status.setIdstatus(1);
            getUtx().begin();
            getAudit().setAction("saved distribution channel " + channels.getName());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            channels.setCreatedby(user);
            channels.setCreatedon(new java.util.Date());
            channels.setStatusid(new Status(3));
            channels.setAgrodealer(agrodealer);
            getEm().persist(getAudit());
            getEm().persist(channels);
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", channels.getName() + " submitted successfully for supervision. wait for response"));
            setChannels(new Distributionchannel());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }

        return null;
    }

    public String updateDistributionchannel() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Distributionchannel prod = getEm().find(Distributionchannel.class, channels.getIddistributionChannel());
            prod = channels;
            prod.setCreatedby(user);
            prod.setCreatedon(new java.util.Date());
            prod.setStatusid(new Status(1));

            getUtx().begin();
            getAudit().setAction("updated channel " + channels.getName());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(prod);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", channels.getName() + " Updated successfully."));
            setChannels(new Distributionchannel());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a product."));
        }
        setChannels(new Distributionchannel());
        return null;
    }

    public String deleteDistributionchannel(Distributionchannel product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted channel");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Distributionchannel toBeRemoved = (Distributionchannel) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "channel deleted", "channel deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
            product = new Distributionchannel();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("User", success);
        }

        return null;
    }

    public String superviseChannels() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Distributionchannel channelss = getEm().find(Distributionchannel.class, getChannels().getIddistributionChannel());
            channelss = this.channels;
            channelss.setStatusid(new Status(2));
            getUtx().begin();
            getAudit().setAction("supervised  " + channels.getName());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(channelss);
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", channelss.getName() + " supervised successfully."));
            setAgrodealers(new Agrodealers());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not supervise the dealer channel."));
        }
        setAgrodealers(new Agrodealers());
        return null;
    }

    public String createAgrodealers() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            status.setIdstatus(1);
            getUtx().begin();
            getAudit().setAction("saved agrodealers " + agrodealers.getCompanyname());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            agrodealers.setCreatedby(user);
            agrodealers.setCreatedon(new java.util.Date());
            agrodealers.setStatusID(status);
            getEm().persist(getAudit());
            getEm().persist(agrodealers);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", agrodealers.getCompanyname() + " saved successfully."));
            setAgrodealers(new Agrodealers());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        setAgrodealers(new Agrodealers());
        return null;
    }

    public String updateAgrodealers() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            User user = getEm().find(User.class, getUser().getIdusers());
            getUtx().begin();
            getAudit().setAction("updated agrodealers " + agrodealers.getCompanyname());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(agrodealers);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", agrodealers.getCompanyname() + " Updated successfully."));
            setAgrodealers(new Agrodealers());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a dealer."));
        }
        setAgrodealers(new Agrodealers());
        return null;
    }

    public String deleteAgrodealers(Agrodealers product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted agrodealers");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Agrodealers toBeRemoved = (Agrodealers) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "agrodealers deleted", "agrodealers deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("agrodealers", success);
            product = new Agrodealers();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("agrodealers", success);
        }
        setAgrodealers(new Agrodealers());
        return null;
    }

    public String createOrders() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }

            orders.setChanneledTo(orders.getChanneledTo());
            orders.setPurchaseID(userpurchase);
            status.setIdstatus(1);
            getUtx().begin();
            getAudit().setAction("saved orders " + orders.getPurchaseID().getAgrodealerproductid().getProductname());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            orders.setCreatedby(user);
            orders.setCreatedon(new java.util.Date());
            orders.setStatusID(new Status(7));
            getEm().persist(getAudit());
            getEm().persist(orders);
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", orders.getPurchaseID().getAgrodealerproductid().getProductname() + " has been sent to " + orders.getChanneledTo().getName() + " successfully."));
            setOrders(new Orders());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        setOrders(new Orders());
        return null;
    }

    public String updateOrders() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Orders order = getEm().find(Orders.class, getOrders().getIdorders());
            order = orders;
            getUtx().begin();
            getAudit().setAction("updated orders " + orders.getPurchaseID().getAgrodealerproductid().getProductname());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(order);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", orders.getPurchaseID().getAgrodealerproductid().getProductname() + " Updated successfully."));
            setOrders(new Orders());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a dealer."));
        }
        setOrders(new Orders());
        return null;
    }

    public String deleteOrders(Orders product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted orders");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Orders toBeRemoved = (Orders) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "orders deleted", "orders deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("orders", success);
            product = new Orders();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("orders", success);
        }
        setOrders(new Orders());
        return null;
    }

    public String createBanks() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("saved banks " + banks.getBankname());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());

            banks.setCreatedby(user);
            banks.setCreatedon(new java.util.Date());
            banks.setStatusID(new Status(1));
            getEm().persist(getAudit());
            getEm().persist(banks);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", banks.getBankname() + " saved successfully."));
            setBanks(new Banks());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
        }
        setBanks(new Banks());
        return null;
    }

    public String updateBanks() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            User user = getEm().find(User.class, getUser().getIdusers());
            getUtx().begin();
            getAudit().setAction("updated banks " + banks.getBankname());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(banks);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", banks.getBankname() + " Updated successfully."));
            setBanks(new Banks());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a dealer."));
        }
        setBanks(new Banks());
        return null;
    }

    public String deleteBanks(Banks product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted banks");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Banks toBeRemoved = (Banks) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "banks deleted", "banks deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("banks", success);
            product = new Banks();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("banks", success);
        }
        setBanks(new Banks());
        return null;
    }

    public String createGovtpayments() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("saved govtpayments " + govtpayments.getPayee());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().persist(govtpayments);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", govtpayments.getPayee() + " saved successfully."));
            setGovtpayments(new Govtpayments());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
        }
        setGovtpayments(new Govtpayments());
        return null;
    }

    public String updateGovtpayments() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            User user = getEm().find(User.class, getUser().getIdusers());
            getUtx().begin();
            getAudit().setAction("updated govtpayments " + govtpayments.getPayee());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(govtpayments);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", govtpayments.getPayee() + " Updated successfully."));
            setGovtpayments(new Govtpayments());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a dealer."));
        }
        setGovtpayments(new Govtpayments());
        return null;
    }

    public String deleteGovtpayments(Govtpayments product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted govtpayments");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Govtpayments toBeRemoved = (Govtpayments) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "govtpayments deleted", "govtpayments deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("govtpayments", success);
            setGovtpayments(new Govtpayments());
            return null;
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("govtpayments", success);
        }
        setGovtpayments(new Govtpayments());
        return null;
    }

    public String createCropproducts() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            cropproduct.setCreatedby(user);
            cropproduct.setCreatedon(new java.util.Date());
            cropproduct.setStatusid(new Status(5));
            getUtx().begin();
            getAudit().setAction("saved govtpayments " + cropproduct.getName());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().persist(cropproduct);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", cropproduct.getName() + " saved successfully."));
            setCropproduct(new Cropproducts());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
        }
        setCropproduct(new Cropproducts());
        return null;
    }

    public String updateCropproducts() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            User user = getEm().find(User.class, getUser().getIdusers());
            getUtx().begin();
            getAudit().setAction("updated cropproduct " + cropproduct.getName());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(cropproduct);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", cropproduct.getName() + " Updated successfully."));
            setCropproduct(new Cropproducts());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a dealer."));
        }
        setCropproduct(new Cropproducts());
        return null;
    }

    public String deleteCropproducts(Cropproducts product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted cropproduct");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Cropproducts toBeRemoved = (Cropproducts) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "cropproduct deleted", "cropproduct deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("cropproduct", success);
            setCropproduct(new Cropproducts());
            return null;
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("cropproduct", success);
        }
        setCropproduct(new Cropproducts());
        return null;
    }

    public String createRegions() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("saved regions " + regions.getProvince());
            getAudit().setCreatedby(getUser());
            getAudit().setTimer(new Date());
            status.setIdstatus(1);
            regions.setCreatedby(user);
            regions.setCreatedon(new java.util.Date());
            regions.setStatusid(status);

            getEm().persist(getAudit());
            getEm().persist(regions);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", regions.getProvince() + " saved successfully."));
            setRegions(new Regions());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
        }
        setRegions(new Regions());
        return null;
    }

    public String updateRegions() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            User user = getEm().find(User.class, getUser().getIdusers());
            getUtx().begin();
            getAudit().setAction("updated regions " + regions.getProvince());
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(regions);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", regions.getProvince() + " Updated successfully."));
            setRegions(new Regions());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a dealer."));
        }
        setRegions(new Regions());
        return null;
    }

    public String deleteRegions(Regions product) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted regions");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Regions toBeRemoved = (Regions) getEm().merge(product);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "regions deleted", "regions deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("regions", success);
            setRegions(new Regions());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("regions", success);
        }
        setRegions(new Regions());
        return null;
    }

    public String createStatus() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("created status");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().persist(getStatus());
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getStatus().getName() + " saved successfully."));
            setStatus(new Status());
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
        setStatus(new Status());
        return null;
    }

    public String updateStatus() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Status status = getEm().find(Status.class, getStatus().getIdstatus());
            getUtx().begin();
            getAudit().setAction("updated segment");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(status);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", status.getName() + " Updated successfully."));
            status = new Status();
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a status."));
        }
        setStatus(new Status());
        return null;
    }

    public String deleteStatus(Status status) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted status");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Status toBeRemoved = (Status) getEm().merge(status);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Status deleted", "Status deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Status", success);
            status = new Status();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Status", success);
        }
        status = new Status();
        return null;
    }

    public String createUsergroup() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            for (String s : responsibilities) {

                getUtx().begin();
                getAudit().setAction("created group");
                getAudit().setCreatedby(user);
                getAudit().setTimer(new Date());
                getEm().persist(getAudit());
                getEm().persist(getGroup1());
                getUtx().commit();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getGroup1().getName() + " saved successfully."));
            group1 = new Usergroup();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        setGroup1(new Usergroup());
        return null;
    }

    public String updateUsergroup() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Usergroup group2 = getEm().find(Usergroup.class, getGroup1().getIdgroups());
            getUtx().begin();
            getAudit().setAction("updated group");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(group2);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", group2.getName() + " Updated successfully."));
            group2 = new Usergroup();
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a user."));
        }
        setGroup1(new Usergroup());
        return null;
    }

    public String deleteUsergroup(Usergroup group) {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted group");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Usergroup toBeRemoved = (Usergroup) getEm().merge(group);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", group.getName() + " Deleted successfully."));
            group = new Usergroup();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", group.getName() + " failed to delete successfully."));
        }
        group = new Usergroup();
        return null;
    }

    public String createUserpurchase() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Ewallet ewal = (Ewallet) em.createQuery("select e from Ewallet e where e.userid.idusers = " + user.getIdusers() + "").getSingleResult();
            Double balance = ewal.getBalance() - (agrodealerProduct.getPrice() - 67 / 100 * agrodealerProduct.getPrice()) * quantity;
            if ((agrodealerProduct.getPrice() - 67 / 100 * agrodealerProduct.getPrice()) * quantity > ewal.getBalance()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Check Balance!", "Dear " + user.getName() + " Sorry you do not have enough amount to purchase " + userpurchase.getAgrodealerproductid().getProductname() + "your current balance is " + ewal.getBalance() + " Topup your wallet to continue purchasing"));
            }
            if (balance <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Dear " + user.getName() + " Sorry you do not have enough balance to purchase " + userpurchase.getAgrodealerproductid().getProductname() + " Topup your wallet to continue purchasing"));
            } else {
                ewal.setBalance(balance);
                userpurchase.setCreatedby(getUser());
                userpurchase.setCreatedon(new java.util.Date());
                userpurchase.setStatusID(new Status(1));
                userpurchase.setUserid(getUser());
                userpurchase.setPrice(agrodealerProduct.getPrice());
                userpurchase.setAgrodealerproductid(agrodealerProduct);
                userpurchase.setQuantity(getQuantity());
                getUtx().begin();
                getAudit().setAction("purchased " + userpurchase.getAgrodealerproductid().getProductname() + " at " + new java.util.Date() + " for " + userpurchase.getPrice());
                getAudit().setCreatedby(getUser());
                getAudit().setPurchase("purchase");
                getAudit().setTimer(new Date());
                getEm().persist(getAudit());
                getEm().merge(ewal);
                getEm().persist(getUserpurchase());
                getUtx().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Congratulations You have successfully purchased " + getQuantity() + " " + getUserpurchase().getAgrodealerproductid().getProductname() + " " + getUserpurchase().getAgrodealerproductid().getAgrodealerproductunit().getUnitname() + " successfully. "
                        + "You can go ahead and scan the barcode label on the commodity purchased to confirm UNBS eligibility, Colect your product from the dealer below"));
                return "successfulPurchase.xhtml";
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
        setUserpurchase(new Userpurchase());
        return null;
    }

    public String updateUserpurchase() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Userpurchase userpurchase = getEm().find(Userpurchase.class, getUserpurchase().getIduserpurchase());
            getUtx().begin();
            getAudit().setAction("updated segment");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            getEm().merge(userpurchase);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getUserpurchase().getAgrodealerproductid().getProductname() + " Updated successfully."));
            setUserpurchase(new Userpurchase());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a userpurchase."));
            logger.info(ex.getMessage());
        }
        setUserpurchase(new Userpurchase());
        return null;
    }

    public static int generateRandomInteger(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min argument must be less than max");
        }
        Random generator = new Random(System.currentTimeMillis()); // see comments!
        int randomNum = generator.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public String ApproveUserpurchase() {
        ParameterStringBuilder psb = new ParameterStringBuilder();
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }

            Userpurchase userpurchase = getEm().find(Userpurchase.class, getUserpurchase().getIduserpurchase());
            getUtx().begin();
            getAudit().setAction("Approved a user purchase");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            userpurchase.setStatusID(new Status(2));
            getEm().persist(getAudit());
            getEm().merge(userpurchase);
            getUtx().commit();

            Integer random = generateRandomInteger(1, 999999999);
            String message = "Password=MaAiF@15062018&UserID=MAAIF&MessageID=" + random.toString() + "&MessageText=Dear " + userpurchase.getCreatedby().getName() + " Your products are available at " + userpurchase.getAgrodealerproductid().getDealerid().getCompanyname() + " Organize to collect them at your own convenience&MobileNumber=" + userpurchase.getCreatedby().getPhone() + "&Priority=low";
            URL myURL = new URL("http://172.17.20.29:32629/ExternalWebService/Service.asmx/SMS?UserID=MAAIF&Password=MaAiF@15062018&MessageID=" + random.toString() + "&MessageText=Your%20products%20available%20collect%20them%20at%20your%20convenience%20MAAIF&MobileNumber=256779221616&Priority=LOW");
            HttpURLConnection connection = (HttpURLConnection) myURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder results = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                results.append(line);
            }

            connection.disconnect();
            System.out.println(results.toString());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getUserpurchase().getAgrodealerproductid().getProductname() + " Approved successfully  message has been sent to " + userpurchase.getCreatedby().getPhone() + " to collect the products"));
            setUserpurchase(new Userpurchase());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            logger.info(ex.getMessage());
        }
        setUserpurchase(new Userpurchase());
        return null;
    }

    public String RejectUserpurchase() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Userpurchase userpurchase = getEm().find(Userpurchase.class, getUserpurchase().getIduserpurchase());
            getUtx().begin();
            getAudit().setAction("Rejected a user purchase");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            userpurchase.setStatusID(new Status(3));
            getEm().persist(getAudit());
            getEm().merge(userpurchase);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getUserpurchase().getAgrodealerproductid().getProductname() + " Updated successfully."));
            setUserpurchase(new Userpurchase());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a userpurchase."));
            logger.info(ex.getMessage());
        }
        setUserpurchase(new Userpurchase());
        return null;
    }

    public String deleteUserpurchase() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setAction("Deleted userpurchase");
            getAudit().setCreatedby(user);
            getAudit().setTimer(new Date());
            getEm().persist(getAudit());
            Userpurchase toBeRemoved = (Userpurchase) getEm().merge(userpurchase);
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted", userpurchase.getAgrodealerproductid().getProductname() + " deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Userpurchase", success);

            userpurchase = new Userpurchase();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Userpurchase", success);
        }
        userpurchase = new Userpurchase();
        return null;
    }

    /**
     * @return the responsibilities
     */
    public String[] getResponsibilities() {
        return responsibilities;
    }

    /**
     * @param responsibilities the responsibilities to set
     */
    public void setResponsibilities(String[] responsibilities) {
        this.setResponsibilities(responsibilities);
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
    public javax.transaction.UserTransaction getUtx() {
        return utx;
    }

    /**
     * @param utx the utx to set
     */
    public void setUtx(javax.transaction.UserTransaction utx) {
        this.utx = utx;
    }

    /**
     * @return the remember
     */
    public Boolean getRemember() {
        return remember;
    }

    /**
     * @param remember the remember to set
     */
    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    /**
     * @return the agrodealerProduct
     */
    public Agrodealerproducts getAgrodealerProduct() {
        userpurchase.setAgrodealerproductid(agrodealerProduct);
        return agrodealerProduct;
    }

    /**
     * @param agrodealerProduct the agrodealerProduct to set
     */
    public void setAgrodealerProduct(Agrodealerproducts agrodealerProduct) {
        this.agrodealerProduct = agrodealerProduct;
    }

    /**
     * @return the agrodealerProductList
     */
    public List<Agrodealerproducts> getAgrodealerProductList() {
        agrodealerProductList = em.createQuery("select a from Agrodealerproducts a").getResultList();
        return agrodealerProductList;
    }

    /**
     * @param agrodealerProductList the agrodealerProductList to set
     */
    public void setAgrodealerProductList(List<Agrodealerproducts> agrodealerProductList) {
        this.agrodealerProductList = agrodealerProductList;
    }

    /**
     * @return the agrodealerproductunits
     */
    public Agrodealerproductunits getAgrodealerproductunits() {
        return agrodealerproductunits;
    }

    /**
     * @param agrodealerproductunits the agrodealerproductunits to set
     */
    public void setAgrodealerproductunits(Agrodealerproductunits agrodealerproductunits) {
        this.agrodealerproductunits = agrodealerproductunits;
    }

    /**
     * @return the AgrodealerproductunitsList
     */
    public List<Agrodealerproductunits> getAgrodealerproductunitsList() {
        AgrodealerproductunitsList = em.createQuery("select a from Agrodealerproductunits a GROUP BY a.unitname").getResultList();
        return AgrodealerproductunitsList;
    }

    /**
     * @param AgrodealerproductunitsList the AgrodealerproductunitsList to set
     */
    public void setAgrodealerproductunitsList(List<Agrodealerproductunits> AgrodealerproductunitsList) {
        this.AgrodealerproductunitsList = AgrodealerproductunitsList;
    }

    /**
     * @return the agrodealers
     */
    public Agrodealers getAgrodealers() {
        return agrodealers;
    }

    /**
     * @param agrodealers the agrodealers to set
     */
    public void setAgrodealers(Agrodealers agrodealers) {
        this.agrodealers = agrodealers;
    }

    /**
     * @return the agrodealersList
     */
    public List<Agrodealers> getAgrodealersList() {
        agrodealersList = em.createQuery("select a from Agrodealers a").getResultList();
        return agrodealersList;
    }

    /**
     * @param agrodealersList the agrodealersList to set
     */
    public void setAgrodealersList(List<Agrodealers> agrodealersList) {
        this.agrodealersList = agrodealersList;
    }

    /**
     * @return the audit
     */
    public Audit getAudit() {
        return audit;
    }

    /**
     * @param audit the audit to set
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
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
     * @return the banks
     */
    public Banks getBanks() {
        return banks;
    }

    /**
     * @param banks the banks to set
     */
    public void setBanks(Banks banks) {
        this.banks = banks;
    }

    /**
     * @return the banksList
     */
    public List<Banks> getBanksList() {
        banksList = em.createQuery("select b from Banks b").getResultList();
        return banksList;
    }

    /**
     * @param banksList the banksList to set
     */
    public void setBanksList(List<Banks> banksList) {
        this.banksList = banksList;
    }

    /**
     * @return the govtpayments
     */
    public Govtpayments getGovtpayments() {
        return govtpayments;
    }

    /**
     * @param govtpayments the govtpayments to set
     */
    public void setGovtpayments(Govtpayments govtpayments) {
        this.govtpayments = govtpayments;
    }

    /**
     * @return the GovtpaymentsList
     */
    public List<Govtpayments> getGovtpaymentsList() {
        GovtpaymentsList = em.createQuery("select  g from Govtpayments g").getResultList();
        return GovtpaymentsList;
    }

    /**
     * @param GovtpaymentsList the GovtpaymentsList to set
     */
    public void setGovtpaymentsList(List<Govtpayments> GovtpaymentsList) {
        this.GovtpaymentsList = GovtpaymentsList;
    }

    /**
     * @return the regions
     */
    public Regions getRegions() {
        return regions;
    }

    /**
     * @param regions the regions to set
     */
    public void setRegions(Regions regions) {
        this.regions = regions;
    }

    /**
     * @return the regionsList
     */
    public List<Regions> getRegionsList() {
        regionsList = em.createQuery("select r from Regions r").getResultList();
        return regionsList;
    }

    /**
     * @param regionsList the regionsList to set
     */
    public void setRegionsList(List<Regions> regionsList) {
        this.regionsList = regionsList;
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

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        userList = em.createQuery("select u from User u").getResultList();
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the group1
     */
    public Usergroup getGroup1() {
        return group1;
    }

    /**
     * @param group1 the group1 to set
     */
    public void setGroup1(Usergroup group1) {
        this.group1 = group1;
    }

    /**
     * @return the group1List
     */
    public List<Usergroup> getGroup1List() {
        group1List = em.createQuery("select u from Usergroup u").getResultList();
        return group1List;
    }

    /**
     * @param group1List the group1List to set
     */
    public void setGroup1List(List<Usergroup> group1List) {
        this.group1List = group1List;
    }

    /**
     * @return the userpurchase
     */
    public Userpurchase getUserpurchase() {
        return userpurchase;
    }

    /**
     * @param userpurchase the userpurchase to set
     */
    public void setUserpurchase(Userpurchase userpurchase) {
        this.userpurchase = userpurchase;
    }

    /**
     * @return the userpurchaseList
     */
    public List<Userpurchase> getUserpurchaseList() {
        userpurchaseList = em.createQuery("select u from Userpurchase u where u.userid.name ='" + user.getName() + "'").getResultList();
        return userpurchaseList;
    }

    /**
     * @param userpurchaseList the userpurchaseList to set
     */
    public void setUserpurchaseList(List<Userpurchase> userpurchaseList) {
        this.userpurchaseList = userpurchaseList;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the agrodealersCount
     */
    public Long getAgrodealersCount() {
        agrodealersCount = ((Long) this.getEm().createNativeQuery("select count(*) from agrodealers").getSingleResult());
        return agrodealersCount;
    }

    /**
     * @param agrodealersCount the agrodealersCount to set
     */
    public void setAgrodealersCount(Long agrodealersCount) {
        this.agrodealersCount = agrodealersCount;
    }

    /**
     * @return the productsCount
     */
    public Long getProductsCount() {
        productsCount = ((Long) this.getEm().createNativeQuery("select count(*) from agrodealerproducts").getSingleResult());
        return productsCount;
    }

    /**
     * @param productsCount the productsCount to set
     */
    public void setProductsCount(Long productsCount) {
        this.productsCount = productsCount;
    }

    /**
     * @return the userCount
     */
    public Long getUserCount() {
        userCount = ((Long) this.getEm().createNativeQuery("select count(*) from user").getSingleResult());
        return userCount;
    }

    /**
     * @param userCount the userCount to set
     */
    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    /**
     * @return the ewallet
     */
    public Ewallet getEwallet() {
        return ewallet;
    }

    /**
     * @param ewallet the ewallet to set
     */
    public void setEwallet(Ewallet ewallet) {
        this.ewallet = ewallet;
    }

    /**
     * @return the ewalletList
     */
    public List<Ewallet> getEwalletList() {
        ewalletList = em.createQuery("select e from Ewallet e where e.userid.name='" + user.getName() + "'").getResultList();

        return ewalletList;
    }

    /**
     * @param ewalletList the ewalletList to set
     */
    public void setEwalletList(List<Ewallet> ewalletList) {
        this.ewalletList = ewalletList;
    }

    /**
     * @return the purchaseAudit
     */
    public List<Audit> getPurchaseAudit() {
        purchaseAudit = em.createQuery("select a from Audit a where a.purchase='purchase'").getResultList();
        return purchaseAudit;
    }

    /**
     * @param purchaseAudit the purchaseAudit to set
     */
    public void setPurchaseAudit(List<Audit> purchaseAudit) {
        this.purchaseAudit = purchaseAudit;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the agrodealer
     */
    public Boolean getAgrodealer() {
        return agrodealer;
    }

    /**
     * @param agrodealer the agrodealer to set
     */
    public void setAgrodealer(Boolean agrodealer) {
        this.agrodealer = agrodealer;
    }

    /**
     * @return the admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * @return the farmer
     */
    public Boolean getFarmer() {
        return farmer;
    }

    /**
     * @param farmer the farmer to set
     */
    public void setFarmer(Boolean farmer) {
        this.farmer = farmer;
    }

    /**
     * @return the government
     */
    public Boolean getGovernment() {
        return government;
    }

    /**
     * @param government the government to set
     */
    public void setGovernment(Boolean government) {
        this.government = government;
    }

    /**
     * @return the users
     */
    public User getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(User users) {
        this.users = users;
    }

    /**
     * @return the unbs
     */
    public Unbs getUnbs() {
        return unbs;
    }

    /**
     * @param unbs the unbs to set
     */
    public void setUnbs(Unbs unbs) {
        this.unbs = unbs;
    }

    /**
     * @return the nira
     */
    public Nira getNira() {
        return nira;
    }

    /**
     * @param nira the nira to set
     */
    public void setNira(Nira nira) {
        this.nira = nira;
    }

    /**
     * @return the skip
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     * @param skip the skip to set
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     * @return the farmers
     */
    public User getFarmers() {
        return farmers;
    }

    /**
     * @param farmers the farmers to set
     */
    public void setFarmers(User farmers) {
        this.farmers = farmers;
    }

    /**
     * @return the blankauditList
     */
    public List<Audit> getBlankauditList() {
        return blankauditList;
    }

    /**
     * @param blankauditList the blankauditList to set
     */
    public void setBlankauditList(List<Audit> blankauditList) {
        this.blankauditList = blankauditList;
    }

    /**
     * @return the blankuserList
     */
    public List<User> getBlankuserList() {
        return blankuserList;
    }

    /**
     * @param blankuserList the blankuserList to set
     */
    public void setBlankuserList(List<User> blankuserList) {
        this.blankuserList = blankuserList;
    }

    public void populateBlankProductList(ValueChangeEvent evt) {
        getBlankagrodealerProductList();
    }

    public void populateVarieties(ValueChangeEvent evt) {
        getVarietiesList();
    }

    public void populateTypes(ValueChangeEvent evt) {
        getProductTypesList();
    }

    /**
     * @return the blankagrodealerProductList
     */
    public List<Agrodealerproducts> getBlankagrodealerProductList() {
        try {
            blankagrodealerProductList = em.createQuery("select a from Agrodealerproducts a where a.dealerid.companyname = '" + agrodealers.getCompanyname() + "'").getResultList();
            return blankagrodealerProductList;
        } catch (Exception Ex) {
            return blankagrodealerProductList;
        }
    }

    /**
     * @param blankagrodealerProductList the blankagrodealerProductList to set
     */
    public void setBlankagrodealerProductList(List<Agrodealerproducts> blankagrodealerProductList) {
        this.blankagrodealerProductList = blankagrodealerProductList;
    }

    /**
     * @return the farmersList
     */
    public List<User> getFarmersList() {
        farmersList = em.createQuery("select u from User u where u.groupID.name ='Farmer'").getResultList();
        return farmersList;
    }

    /**
     * @param farmersList the farmersList to set
     */
    public void setFarmersList(List<User> farmersList) {
        this.farmersList = farmersList;
    }

    /**
     * @return the blankfarmersList
     */
    public List<User> getBlankfarmersList() {
        return blankfarmersList;
    }

    /**
     * @param blankfarmersList the blankfarmersList to set
     */
    public void setBlankfarmersList(List<User> blankfarmersList) {
        this.blankfarmersList = blankfarmersList;
    }

    /**
     * @return the agrodealerpurchaseList
     */
    public List<Userpurchase> getAgrodealerpurchaseList() {
        agrodealerpurchaseList = em.createQuery("select u from Userpurchase u where u.agrodealerproductid.dealerid.ownerid.idusers = " + user.getIdusers() + "").getResultList();
        return agrodealerpurchaseList;
    }

    /**
     * @param agrodealerpurchaseList the agrodealerpurchaseList to setorder
     */
    public void setAgrodealerpurchaseList(List<Userpurchase> agrodealerpurchaseList) {
        this.agrodealerpurchaseList = agrodealerpurchaseList;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the orders
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    /**
     * @return the orderslist
     */
    public List<Orders> getOrderslist() {
        orderslist = em.createQuery("select o from Orders o where o.purchaseID.agrodealerproductid.dealerid.ownerid.name = '" + user.getName() + "'").getResultList();
        return orderslist;
    }

    /**
     * @param orderslist the orderslist to set
     */
    public void setOrderslist(List<Orders> orderslist) {
        this.orderslist = orderslist;
    }

    /**
     * @return the channels
     */
    public Distributionchannel getChannels() {
        return channels;
    }

    /**
     * @param channels the channels to set
     */
    public void setChannels(Distributionchannel channels) {
        this.channels = channels;
    }

    /**
     * @return the channelList
     */
    public List<Distributionchannel> getChannelList() {
        channelList = em.createQuery("select d from Distributionchannel d where d.agrodealer.ownerid.name = '" + user.getName() + "' and d.statusid.name = 'supervised'").getResultList();
        return channelList;
    }

    /**
     * @param channelList the channelList to set
     */
    public void setChannelList(List<Distributionchannel> channelList) {
        this.channelList = channelList;
    }

    /**
     * @return the unsupervisedchannelList
     */
    public List<Distributionchannel> getUnsupervisedchannelList() {
        unsupervisedchannelList = em.createQuery("select d from Distributionchannel d where d.statusid.name = 'unsupervised'").getResultList();
        return unsupervisedchannelList;
    }

    /**
     * @param unsupervisedchannelList the unsupervisedchannelList to set
     */
    public void setUnsupervisedchannelList(List<Distributionchannel> unsupervisedchannelList) {
        this.unsupervisedchannelList = unsupervisedchannelList;
    }

    /**
     * @return the varietiesList
     */
    public List<Agrodealerproducts> getVarietiesList() {
        try {
            varietiesList = em.createQuery("select a from Agrodealerproducts a where a.producttype.productname = 'seeds'").getResultList();
            return varietiesList;
        } catch (Exception ex) {
            return varietiesList;
        }
    }

    /**
     * @param varietiesList the varietiesList to set
     */
    public void setVarietiesList(List<Agrodealerproducts> varietiesList) {
        this.varietiesList = varietiesList;
    }

    /**
     * @return the types
     */
    public Agrodealerproducttypes getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(Agrodealerproducttypes types) {
        this.types = types;
    }

    /**
     * @return the productTypesList
     */
    public List<Agrodealerproducttypes> getProductTypesList() {
        productTypesList = em.createQuery("select a from Agrodealerproducttypes a").getResultList();
        return productTypesList;
    }

    /**
     * @param productTypesList the productTypesList to set
     */
    public void setProductTypesList(List<Agrodealerproducttypes> productTypesList) {
        this.productTypesList = productTypesList;
    }

    /**
     * @return the availableTypesList
     */
    public List<Agrodealerproducts> getAvailableTypesList() {
        try {
            setAvailableTypesList((List<Agrodealerproducts>) em.createQuery("select a from Agrodealerproducts a where a.producttype ='" + types.getProductname() + "'").getResultList());
        } catch (Exception Ex) {
            return getAvailableTypesList();
        }
        return availableTypesList;
    }

    /**
     * @param availableTypesList the availableTypesList to set
     */
    public void setAvailableTypesList(List<Agrodealerproducts> availableTypesList) {
        this.availableTypesList = availableTypesList;
    }

    /**
     * @return the packageList
     */
    public List<Agrodealerproducts> getPackageList() {
        try {
            packageList = em.createQuery("select a from Agrodealerproducts a where a.productpackage.productname = '" + agrodealerProduct.getProductname() + "'").getResultList();
        } catch (Exception Ex) {
            return getAvailableTypesList();
        }
        return packageList;
    }

    /**
     * @param packageList the packageList to set
     */
    public void setPackageList(List<Agrodealerproducts> packageList) {
        this.packageList = packageList;
    }

    /**
     * @return the cartList
     */
    public List<Userpurchase> getCartList() {
        totalPrice = 0.00;
        farmerPays = 0.00;
        subsidizedvalue = 0.00;
        try {
            cartList = em.createQuery("select u from Userpurchase u where u.statusID.name = 'cart' and u.userid.name ='" + user.getName() + "'").getResultList();
            for (Userpurchase up : cartList) {
                Double subsidy = (0.67 * up.getPrice());
                totalPrice = totalPrice + up.getPrice();
                farmerPays = farmerPays + (up.getPrice() - subsidy);
                subsidizedvalue = subsidizedvalue + subsidy;
            }
            return cartList;
        } catch (Exception ex) {
            return cartList;
        }
    }

    /**
     * @param cartList the cartList to set
     */
    public void setCartList(List<Userpurchase> cartList) {
        this.cartList = cartList;
    }

    /**
     * @return the totalPrice
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the subsidizedvalue
     */
    public Double getSubsidizedvalue() {
        return subsidizedvalue;
    }

    /**
     * @param subsidizedvalue the subsidizedvalue to set
     */
    public void setSubsidizedvalue(Double subsidizedvalue) {
        this.subsidizedvalue = subsidizedvalue;
    }

    /**
     * @return the farmerPays
     */
    public Double getFarmerPays() {
        return farmerPays;
    }

    /**
     * @param farmerPays the farmerPays to set
     */
    public void setFarmerPays(Double farmerPays) {
        this.farmerPays = farmerPays;
    }

    public Long getMalefarmers() {
        malefarmers = ((Long) this.getEm().createNativeQuery("select count(*) from user where gender='Male'").getSingleResult());
        return malefarmers;
    }

    /**
     * @param malefarmers the malefarmers to set
     */
    public void setMalefarmers(Long malefarmers) {
        this.malefarmers = malefarmers;
    }

    /**
     * @return the femalefarmers
     */
    public Long getFemalefarmers() {
        femalefarmers = ((Long) this.getEm().createNativeQuery("select count(*) from user where gender='Female'").getSingleResult());
        return femalefarmers;
    }

    /**
     * @param femalefarmers the femalefarmers to set
     */
    public void setFemalefarmers(Long femalefarmers) {
        this.femalefarmers = femalefarmers;
    }

    /**
     * @return the UBA
     */
    public Boolean getUBA() {
        return UBA;
    }

    /**
     * @param UBA the UBA to set
     */
    public void setUBA(Boolean UBA) {
        this.UBA = UBA;
    }

    /**
     * @return the cropproduct
     */
    public Cropproducts getCropproduct() {
        return cropproduct;
    }

    /**
     * @param cropproduct the cropproduct to set
     */
    public void setCropproduct(Cropproducts cropproduct) {
        this.cropproduct = cropproduct;
    }

    /**
     * @return the cropproductList
     */
    public List<Cropproducts> getCropproductList() {
        cropproductList = em.createQuery("select c from Cropproducts c").getResultList();
        return cropproductList;
    }

    /**
     * @param cropproductList the cropproductList to set
     */
    public void setCropproductList(List<Cropproducts> cropproductList) {
        this.cropproductList = cropproductList;
    }

    /**
     * @return the regionModel
     */
    public BarChartModel getRegionModel() {
        return regionModel;
    }

    /**
     * @param regionModel the regionModel to set
     */
    public void setRegionModel(BarChartModel regionModel) {
        this.regionModel = regionModel;
    }

    /**
     * @return the cropModel
     */
    public BarChartModel getCropModel() {
        return cropModel;
    }

    /**
     * @param cropModel the cropModel to set
     */
    public void setCropModel(BarChartModel cropModel) {
        this.cropModel = cropModel;
    }

    /**
     * @return the farmergroups
     */
    public Farmergroups getFarmergroups() {

        return farmergroups;
    }

    /**
     * @param farmergroups the farmergroups to set
     */
    public void setFarmergroups(Farmergroups farmergroups) {
        this.farmergroups = farmergroups;
    }

    /**
     * @return the farmergroupsList
     */
    public List<Farmergroups> getFarmergroupsList() {
        farmergroupsList = em.createQuery("select f from Farmergroups f").getResultList();
        return farmergroupsList;
    }

    /**
     * @param farmergroupsList the farmergroupsList to set
     */
    public void setFarmergroupsList(List<Farmergroups> farmergroupsList) {
        this.farmergroupsList = farmergroupsList;
    }

    /**
     * @return the Regionmodel
     */
    public List<Regionmodel> getRegionmodel() {
        return Regionmodel;
    }

    /**
     * @param Regionmodel the Regionmodel to set
     */
    public void setRegionmodel(List<Regionmodel> Regionmodel) {
        this.Regionmodel = Regionmodel;
    }

    /**
     * @return the Cropmodel
     */
    public List<Cropmodel> getCropmodel() {
        return Cropmodel;
    }

    /**
     * @param Cropmodel the Cropmodel to set
     */
    public void setCropmodel(List<Cropmodel> Cropmodel) {
        this.Cropmodel = Cropmodel;
    }

    /**
     * @return the agrodealersperRegion
     */
    public BarChartModel getAgrodealersperRegion() {
        return agrodealersperRegion;
    }

    /**
     * @param agrodealersperRegion the agrodealersperRegion to set
     */
    public void setAgrodealersperRegion(BarChartModel agrodealersperRegion) {
        this.agrodealersperRegion = agrodealersperRegion;
    }

    /**
     * @return the dealersPerregion
     */
    public List<Agrodealersperregion> getDealersPerregion() {
        return dealersPerregion;
    }

    /**
     * @param dealersPerregion the dealersPerregion to set
     */
    public void setDealersPerregion(List<Agrodealersperregion> dealersPerregion) {
        this.dealersPerregion = dealersPerregion;
    }

    /**
     * @return the agrodealerProductListUnchecked
     */
    public List<Agrodealerproducts> getAgrodealerProductListUnchecked() {
        agrodealerProductListUnchecked = em.createQuery("select a from Agrodealerproducts a where a.status.name = 'added'").getResultList();
        return agrodealerProductListUnchecked;
    }

    /**
     * @param agrodealerProductListUnchecked the agrodealerProductListUnchecked
     * to set
     */
    public void setAgrodealerProductListUnchecked(List<Agrodealerproducts> agrodealerProductListUnchecked) {
        this.agrodealerProductListUnchecked = agrodealerProductListUnchecked;
    }

    /**
     * @return the generalCartList
     */
    public List<Userpurchase> getGeneralCartList() {
        generalCartList = em.createQuery("select u from Userpurchase u where u.statusID.name = 'cart'").getResultList();
        return generalCartList;
    }

    /**
     * @param generalCartList the generalCartList to set
     */
    public void setGeneralCartList(List<Userpurchase> generalCartList) {
        this.generalCartList = generalCartList;
    }

    /**
     * @return the cvv
     */
    public Boolean getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(Boolean cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the accountNumber
     */
    public Boolean getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(Boolean accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the phoneNumber
     */
    public Boolean getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(Boolean phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the accpassword
     */
    public Boolean getAccpassword() {
        return accpassword;
    }

    /**
     * @param accpassword the accpassword to set
     */
    public void setAccpassword(Boolean accpassword) {
        this.accpassword = accpassword;
    }

    /**
     * @return the availableTypesList
     */
}
