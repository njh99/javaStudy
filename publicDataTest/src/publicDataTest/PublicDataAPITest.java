package publicDataTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import publicDataTest.controll.LandPriceRegisterManager;
import publicDataTest.model.LandPriceVO;
import publicDataTest.view.LANDPRICE_CHOICE;
import publicDataTest.view.MenuViewer;

public class PublicDataAPITest {
	public static Scanner sc = new Scanner(System.in);
	@SuppressWarnings("deprecation")
	
	public static void main(String[] args) {
        int no;
        boolean exitFlag = false;
        LandPriceRegisterManager lrm = new LandPriceRegisterManager();
        while (!exitFlag) {
            try {
                MenuViewer.mainMenuView();
                no = Integer.parseInt(sc.nextLine()); 
                switch (no) {
                case LANDPRICE_CHOICE.LOAD:
                    ArrayList<LandPriceVO> list = apiDataLoad();
                    printLandPriceList(list);
                    break;
                case LANDPRICE_CHOICE.INSERT:
                	lrm.InsertManager();
                    break;
                case LANDPRICE_CHOICE.SELECT:
                	lrm.selectManager();
                    break;
                case LANDPRICE_CHOICE.DELETE:
                	lrm.deleteManager();
                	 break;
                case LANDPRICE_CHOICE.UPDATE:
                	lrm.UpdateManager();
                    break;
                case LANDPRICE_CHOICE.EXIT:
                    System.out.println("프로그램을 종료합니다.");
                    exitFlag = true; 
                    break; 
                default:
                    System.out.println("해당 메뉴 번호만 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
            }
        }//end of file
    }

	public static void printLandPriceList(ArrayList<LandPriceVO> landPriceList) {
		for (LandPriceVO data : landPriceList) {
			System.out.println(data);
		}
		
	}
	
	public static ArrayList<LandPriceVO> apiDataLoad() {
		// 1. url 주소를 적는다.
		StringBuilder urlBuilder = null;
		ArrayList<LandPriceVO> landPriceList = new ArrayList<LandPriceVO>();
		try {
			urlBuilder = new StringBuilder("https://apis.data.go.kr/1613000/BusSttnInfoInqireService/getSttnNoList");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=V6BptpKcWS7Gdvwcgpa0ycm1ft%2BGHTIFoBNyVmWr0R%2BRmZ3YYZorCTu114wmVsVHmQGVGNRoAn9ljOFPqkEWFg%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cityCode", "UTF-8") + "=" + URLEncoder.encode("25", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("nodeNm", "UTF-8") + "=" + URLEncoder.encode("전통시장", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("nodeNo", "UTF-8") + "=" + URLEncoder.encode("44810", "UTF-8"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		// 2. 공공데이터 connection 객체 생성(method방식, Content-type) 요청성공유무
		// conn.getResponseCode()확인
		URL url = null;
		HttpURLConnection conn = null;
		try {
			// con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe",
			// "hr", "hr");
			url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

		} catch (MalformedURLException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// 3. 요청전송, 응답처리(200~300: 정상, 403:권한설정, 404: 페이지를 찾기 힘듬, 405:인증안됨,500: 서버에서
		// 예외처리)
		BufferedReader br = null;
		try {
			// 서버요청 성공코드가 오면 message Body 읽기위해서 bufferedReader 설정하고
			// 서버요청 실패코드가 오면 message Body 에러메시지읽기위해서 준비
			int responseCode = conn.getResponseCode();
			if (responseCode >= 200 && responseCode <= 300) {
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
			} else {
				InputStream is = conn.getErrorStream();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
			}
			// message body 데이터를 가져오자
			Document doc = parseXML(conn.getInputStream());
			// doc 객체속에서 item 태그명을 찾는다.(태그 객체 = 노드)
			NodeList parentNode = doc.getElementsByTagName("item");
			// 객체를 가져오는데 디비에서 가져오는게 아니라 공공데이터서버에서 네트워크로 가져오는 것.
			
			// parentNode 객체에서 자식 객체를 가죠오기
			for (int i = 0; i < parentNode.getLength(); i++) {
				// 자식객체 가져오기
				Node item = parentNode.item(i);
				LandPriceVO landPrice = new LandPriceVO();
				// 자식의 이름을 검색해서 거기에 해당되는 데이터를 가져와 모델VO LandPriceVO입력한다.
				// Node에는 <div 속성>context(내용, 자식노드)</div>
				for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
					switch (node.getNodeName()) {
					case "gpslati":
						landPrice.setGpslati(Double.parseDouble(node.getTextContent()));
						break;
					case "gpslong":
						landPrice.setGpslong(Double.parseDouble(node.getTextContent()));
						break;
					case "nodeid":
						landPrice.setNodeid(node.getTextContent());
						break;
					case "nodenm":
						landPrice.setNodenm(node.getTextContent());
						break;
					case "nodeno":
						landPrice.setNodeno(Integer.parseInt(node.getTextContent()));
						break;

					}
				}
				landPriceList.add(landPrice);

			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return landPriceList;
	}
	// 입력에 해당되는 객체를 주면 Document 변환해서 리턴한다.

	public static Document parseXML(InputStream stream) {
		DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(stream);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) { // Simple API for XML e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
