package Running;

import com.relevantcodes.extentreports.ExtentTest;

import Common.Browser;
import Keywords.AlertBox;
import Keywords.Checkbox;
import Keywords.Clear;
import Keywords.Click;
import Keywords.CopyAndPaste;
import Keywords.Default;
import Keywords.Details;
import Keywords.Download;
import Keywords.Dropdown;
import Keywords.ElementState;
import Keywords.FinancialView;
import Keywords.ForecastTable;
import Keywords.ForecastTableValidation;
import Keywords.Frames;
import Keywords.GettingText;
import Keywords.GoToUrl;
import Keywords.ListObjects;
import Keywords.Menu;
import Keywords.MouseHover;
import Keywords.MultipleTableValidation;
import Keywords.OpportunityManagement;
import Keywords.Popup;
import Keywords.Print;
import Keywords.RelationshipMap;
import Keywords.Repeat;
import Keywords.RevenueProjection;
import Keywords.ScreenEnable;
import Keywords.Scrolling;
import Keywords.Size;
import Keywords.TableValidation;
import Keywords.Tabs;
import Keywords.Upload;
import Keywords.VariableClass;
import Keywords.VerifyText;
import Keywords.Waiting;
import Keywords.WebElementKeyword;
import Keywords.WebElementList;
import Keywords.WriteText;
//import jdk.jfr.internal.PrivateAccess;


public class KeywordObjects {
	private GoToUrl goToUrl;
	private WriteText writeText;
	private Click click;
	private Popup popup;
	private Dropdown dropdown;
	private Checkbox checkBox;
	private AlertBox alertBox;
	private VerifyText verifyText;
	private MouseHover mouseHover;
	private Scrolling scrolling;
	private ListObjects listObjects;
	private Upload upload;
	private ScreenEnable screenEnable;
	private Size size;
	private Tabs tabs;
	private ElementState elementState;
	private Menu menu;
	private Download download;
	private CopyAndPaste copyAndPaste;
	private Repeat repeat;
	private Frames frames;
	private Waiting waiting;
	private Details details;
	private Default def;
	private Print print;
	private ForecastTable forecastTable;
	private ForecastTableValidation forecastTableValidation;
    private WebElementKeyword webElement;
    private WebElementList webElements;
    private VariableClass variable;
    private TableValidation tableValidation;
    private MultipleTableValidation multipletablevalidation;
    private FinancialView financialView;
    private GettingText gettingText;
    private Clear clear;
    private RelationshipMap relationshipMap;
    private RevenueProjection revenueProjection;
    private OpportunityManagement oppmang;
    
	
	ExtentTest test;
	Browser browser;

	public KeywordObjects(Browser browser, ExtentTest test) {
		this.browser = browser;
		this.test = test;
	}

	public GoToUrl getGoToUrl() throws Exception {
		return (goToUrl == null) ? new GoToUrl(browser.getWebDriver(), test) : goToUrl;
	}

	public WriteText getWriteText() throws Exception {
		return (writeText == null) ? new WriteText(browser.getWebDriver(), test) : writeText;
	}

	public Click getClick() throws Exception {
		return (click == null) ? new Click(browser.getWebDriver(), test) : click;
	}
	public Clear getClear() throws Exception {
		return (clear == null) ? new Clear(browser.getWebDriver(), test) : clear;
	}

	public Popup getPopup() throws Exception {
		return (popup == null) ? new Popup(browser.getWebDriver(), test) : popup;
	}

	public Dropdown getDropdown() throws Exception {
		return (dropdown == null) ? new Dropdown(browser.getWebDriver(), test) : dropdown;
	}

	public Checkbox getCheckBox() throws Exception {
		return (checkBox == null) ? new Checkbox(browser.getWebDriver(), test) : checkBox;
	}

	public AlertBox getAlertBox() throws Exception {
		return (alertBox == null) ? new AlertBox(browser.getWebDriver(), test) : alertBox;
	}

	public VerifyText getVerifyText() throws Exception {
		return (verifyText == null) ? new VerifyText(browser.getWebDriver(), test) : verifyText;
	}

	public MouseHover getMouseHover() throws Exception {
		return (mouseHover == null) ? new MouseHover(browser.getWebDriver(), test) : mouseHover;
	}

	public Scrolling getScrolling() throws Exception {
		return (scrolling == null) ? new Scrolling(browser.getWebDriver(), test) : scrolling;
	}

	public ListObjects getListObjects() throws Exception {
		return (listObjects == null) ? new ListObjects(browser.getWebDriver(), test) : listObjects;
	}

	public Upload getUpload() throws Exception {
		return (upload == null) ? new Upload(browser.getWebDriver(), test) : upload;
	}

	public ScreenEnable getScreenEnable() throws Exception {
		return (screenEnable == null) ? new ScreenEnable(browser.getWebDriver(), test) : screenEnable;
	}

	public Size getSize() throws Exception {
		return (size == null) ? new Size(browser.getWebDriver(), test) : size;
	}

	public Tabs getTabs() throws Exception {
		return (tabs == null) ? new Tabs(browser.getWebDriver(), test) : tabs;
	}

	public ElementState getelementState() throws Exception {
		return (elementState == null) ? new ElementState(browser.getWebDriver(), test) : elementState;
	}

	public Menu getMenu() throws Exception {
		return (menu == null) ? new Menu(browser.getWebDriver(), test) : menu;
	}

	public Download getDownload() throws Exception {
		return (download == null) ? new Download(browser.getWebDriver(), test) : download;
	}

	public CopyAndPaste getCopyAndPaste() throws Exception {
		return (copyAndPaste == null) ? new CopyAndPaste(browser.getWebDriver(), test) : copyAndPaste;
	}

	public Repeat getRepeat() throws Exception {
		return (repeat == null) ? new Repeat(browser.getWebDriver(), test) : repeat;
	}

	public Frames getFrames() throws Exception {
		return (frames == null) ? new Frames(browser.getWebDriver(), test) : frames;
	}

	public Waiting getWaiting() throws Exception {
		return (waiting == null) ? new Waiting(browser.getWebDriver()) : waiting;
	}

	public Details getDetails() throws Exception {
		return (details == null) ? new Details(browser.getWebDriver(), test) : details;
	}

	public Default getDef() throws Exception {
		return (def == null) ? new Default(browser.getWebDriver(), test) : def;
	}

	public ForecastTable getForecastTable() throws Exception {
		return (forecastTable == null) ? new ForecastTable(browser.getWebDriver(), test) : forecastTable;
	}

	public ForecastTableValidation getForecastTableValidation() throws Exception {
		return (forecastTableValidation == null) ? new ForecastTableValidation(browser.getWebDriver(), test) : forecastTableValidation;
	}

	public Print getPrint() throws Exception {
		return (print == null) ? new Print(browser.getWebDriver(), test) : print;
	}
	
	public WebElementKeyword getWebElementKeyword() throws Exception {
		return (webElement == null) ? new WebElementKeyword(browser.getWebDriver(), test) : webElement;		
	}
	
	public VariableClass getVariable() throws Exception {
		return (variable == null) ? new VariableClass() : variable;
	}
	
	public WebElementList getWebElementList() throws Exception {
		return (webElements == null) ? new WebElementList(browser.getWebDriver(), test) : webElements;	
	}
	
	public TableValidation getTableValidation() throws Exception {
		return (tableValidation == null) ? new TableValidation(browser.getWebDriver(), test) : tableValidation;
	}
	public MultipleTableValidation getMultipleTableValidation() throws Exception {
		return (multipletablevalidation == null) ? new MultipleTableValidation(browser.getWebDriver(), test) : multipletablevalidation;
	}
	
	public FinancialView getFinancialView() throws Exception {
		return (financialView == null) ? new FinancialView(browser.getWebDriver(), test) : financialView;
	}
	public RelationshipMap getRelationshipMap() throws Exception {
		return (relationshipMap == null) ? new RelationshipMap(browser.getWebDriver(), test) : relationshipMap;
	}
	public GettingText getGettingText() throws Exception {
		return (gettingText == null) ? new GettingText(browser.getWebDriver(), test) : gettingText;
	}
	
	public RevenueProjection getRevenueProjection() throws Exception{
		return (revenueProjection == null) ? new RevenueProjection(browser.getWebDriver(), test) : revenueProjection;
}
	public OpportunityManagement getOpportunityManagement() throws Exception{
		return (oppmang == null) ? new OpportunityManagement(browser.getWebDriver(), test) : oppmang;
}
}