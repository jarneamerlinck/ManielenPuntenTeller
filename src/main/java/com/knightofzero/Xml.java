package com.knightofzero;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.*;

public class Xml {
    private DocumentBuilderFactory factory;
    private Document doc;
    private DocumentBuilder builder;
    public String path;
    private String W1;
    private String W2;
    private String Z1;
    private String Z2;
    private String[][] players;//{{ID,name},...}
    private String GameNr;
    private int boomCurrent=0;

    /**
     *  Starts reading the file and checking if the file is there
     * @param path the relative or absolute path to the '.xml' file
     */
    //TODO Looping wegwerken streams or Lambda functions
    public Xml(String path) {
        this.path=path;
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(path);
            getAllInformation(false);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * returns the name of the first player of the first team
     * @return a String
     */
    public String getW_Player1() {
        return W1;
    }
    /**
     * Sets the name of the first player of the first team
     * @param Name The name of the player
     */
    public void setW_Player1(String Name) {
        W1 = Name;
        getPlayerID(Name);
    }
    /**
     * returns the name of the second player of the first team
     * @return a String
     */
    public String getW_Player2() {
        return W2;
    }
    /**
     * Sets the name of the second player of the first team
     * @param Name The name of the player
     */
    public void setW_Player2(String Name) {
        W2 = Name;
        getPlayerID(Name);
    }
    /**
     * returns the name of the first player of the second team
     * @return a String
     */
    public String getZ_Player1() {
        return Z1;
    }
    /**
     * Sets the name of the first player of the second team
     * @param Name The name of the player
     */
    public void setZ_Player1(String Name) {
        Z1 = Name;
        getPlayerID(Name);
    }
    /**
     * returns the name of the second player of the second team
     * @return a String
     */
    public String getZ2_Player2() {
        return Z2;
    }
    /**
     * Sets the name of the second player of the second team
     * @param Name The name of the player
     */
    public void setZ_Player2(String Name) {
        Z2 = Name;
        getPlayerID(Name);
    }
    /**
     * used to print a 2 dimensionale matrix
     * @param x a 2 dimensionale matrix
     */
    private static void display(String x[][]) {
        for (int row = 0; row < x.length; row++) {
            for (int column = 0; column < x[row].length; column++) {
                System.out.print(x[row][column] + "\t");
            }
            System.out.println();
        }
    }
    private static void display(HashMap<String,String[]> toPrint) {
        for (String[] i :toPrint.values()) {
            for (int j = 0; j < i.length; j++) {
                System.out.print(i[j] + "\t");
            }
            System.out.println();
        }
    }
    /**
     * finds the player ID in the '.xml' file or when not found makes the I.
     * The ID is always unique
     * @param PlayerName Name of the player in a String type
     * @return Value of teh playerID in a String type
     */
    private String getPlayerID(String PlayerName) {
        boolean Found = false;
        String ID ="";
        NodeList personList = doc.getElementsByTagName("person");
        for (int i = 0; i < personList.getLength(); i++) {
            Node p = personList.item(i);
            if (p.getNodeType()==Node.ELEMENT_NODE) {
                Element person = (Element) p;
                NodeList PersonUnderList = person.getChildNodes();
                for (int j = 0; j < PersonUnderList.getLength(); j++) {
                    Node name = PersonUnderList.item(j);
                    if (name.getNodeType()==Node.ELEMENT_NODE) {
                        Element n = (Element) name;
                        if (n.getTagName().equalsIgnoreCase("name") && n.getTextContent().equalsIgnoreCase(PlayerName)) {
                            System.out.println(String.format("For the player %s, the ID is %s", n.getTextContent(),person.getAttribute("id")));
                            Found=true;
                            ID=person.getAttribute("id");
                        }
                    }
                }

            }

        }
        if (!Found) {
            makePlayer(Integer.toString(personList.getLength()) , PlayerName);
            return Integer.toString(personList.getLength());
        }
        else {
            return ID;
        }

    }
    /**
     * Returns the number of players in the '.xml' file
     * @return int with a value equal to the number of players
     */
    public int getNumberofPlayers() {
        NodeList personList = doc.getElementsByTagName("person");
        return personList.getLength();
    }
    private Node getNode(String name) {
        NodeList nodelist=doc.getElementsByTagName(name);
        if (nodelist.getLength()!=1)
            System.out.println("To many options");
        else {

            return nodelist.item(0);
        }
        return null;
    }
    private void makePlayer(String ID, String name) {

        Node people = getNode("people");
        Element person = doc.createElement("person");
        person.setAttribute("id", ID);
        person.appendChild(makeElements(person, "name", name));
        person.appendChild(makeElements(person, "wins", "0"));
        //person.appendChild(makeElements(person, "rank", rankMax()));
        person.appendChild(makeElements(person, "rank", "0"));
        people.appendChild(person);

        try {
            save();
        } catch (TransformerException e) {
            e.printStackTrace();

        }
        System.out.println(String.format("Player Made with the ID of %s and the name %s", ID,name));

    }
    /**
     * Makes a new ID when the player is not found
     * @return String unique to other ID's
     */
    private String findNewID() {//has to be private after testing
        int NewID=0;
        System.out.println(String.format("players lengt= %d", players.length));

        return String.format("%d", NewID);
    }
    private  Node makeElements(Element element,String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    /**
     * Tests the file for errors needs to be in a try loop to see errors.
     * Set print to true and all the players with there wins,losses and ranks will be printed
     * @param print boolean set to true when require printing
     */
    public void getAllInformation(boolean print) {
        String[] player;
        player = new String[4];
        int stri=0;
        int playernumber=0;
        NodeList personList = doc.getElementsByTagName("person");
        players = new String[personList.getLength()][4];
        for (int i = 0; i < personList.getLength(); i++) {
            Node p = personList.item(i);
            if (p.getNodeType()==Node.ELEMENT_NODE) {
                Element person = (Element) p;
                player[0]=person.getAttribute("id");
                stri++;
                NodeList PersonUnderList = person.getChildNodes();
                for (int j = 0; j < PersonUnderList.getLength(); j++) {
                    Node name = PersonUnderList.item(j);
                    if (name.getNodeType()==Node.ELEMENT_NODE) {
                        Element n = (Element) name;
                        player[stri]=n.getTextContent();
                        stri++;
                    }
                }
                for (int j = 0; j < player.length; j++) {
                    players[playernumber][j] = player[j];
                }
                playernumber++;
                stri=0;
            }
        }
        if (print)
            display(players);
    }
    public void getAllInformationNew(boolean print) {
        String[] player;
        player = new String[4];
        HashMap<String, String[]> players = new HashMap<>();
        int stri = 0;
        NodeList personList = doc.getElementsByTagName("person");
        //Test1 looping
		/*
		looping nr1 = new looping(stri) {
			@Override
			public String toRun(NodeList nodelist) {
				//in nr1
				looping nr2 = new looping(getIndex()) {
					public int indexes=0;
					@Override
					public String toRun(NodeList nodelist) {
						//in nr2
						//Nodes
						Node item = nodelist.item(getIndex());
						//System.out.print(item.getTextContent() +"\t");
						System.out.print(item.getNodeType()==Node.ELEMENT_NODE);
						if(item.getNodeType()==Node.ELEMENT_NODE){
							indexes++;
							System.out.println(item.getTextContent());
							return item.getTextContent();
							}
						else {
							return "";
						}

				};
				String[] Value=new String[4];
				try {
					players.put(Integer.toString(getIndex()),nr2.run(nodelist,4));setIndex(getIndex()+1);
				}catch (Exception e){
					System.out.println(e);
				}
				System.out.println();
			return "Done";
			}
		};
		nr1.run(personList,4);
		if (print)
			display(players);
	}*/
        //Deel2
        Looping loop = new Looping(stri) {
            @Override
            public String toRun(NodeList nodelist) {
                //Gww  de waardes
		/*
				NodeList nl2=getChilds(nodelist);
				Node arg;
				arg=nodelist.item(0);
				ArrayList<Element> elements=getElements(nl2);
				System.out.println("Size="+elements.size());
				if (elements.size()>0){
				String print=arg.getAttributes().item(0).toString().replace("id=","");
				print=print.substring(1,print.length()-1)+"\t";
				for (int i = 0; i < elements.size(); i++) {
					print+=String.format("%s\t",elements.get(i).getTextContent());
				}
				//printAll(nodelist);
				return print;}
				else return null;*/
                //ArrayList nl2=  getNodes(nodelist);

                return null;//nl2.get(0).toString();
            }
        };
        System.out.println(String.format("-----\nHere Starts the new version\n%s",loop.toRun(personList)));
        //loop.printAll(personList);

    }

    /**
     * Makes a new game in the '.xml' file
     */
    public void makegame() {
        Node score = getNode("scores");
        Element game = doc.createElement("game");
        Element W = doc.createElement("W");
        Element Z = doc.createElement("Z");
        W.setAttribute("id1", getPlayerID(W1));
        W.setAttribute("id2", getPlayerID(W2));
        Z.setAttribute("id1", getPlayerID(Z1));
        Z.setAttribute("id2", getPlayerID(Z2));

        String ID = Integer.toString(doc.getElementsByTagName("game").getLength());
        game.setAttribute("nr", ID);
        GameNr=ID;
        game.appendChild(makeElements(game, "gameScore", "0-0"));

        game.appendChild(W);
        game.appendChild(Z);
        score.appendChild(game);

        try {
            save();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Game Had been made with ID %s", ID));

    }
    /**
     * Saves the results of the game
     * @param EndPoints String in the format :'x-y' x and y are ints example: '76-105'
     */
    public void saveboom(String EndPoints) {
        System.out.println(EndPoints);
        Element boom =doc.createElement("boom");
        boom.appendChild(makeElements(boom, "EndPoints", EndPoints));
        NodeList all = doc.getElementsByTagName("game");
        for (int i = 0; i < all.getLength(); i++) {
            Element p = (Element) all.item(i);
            if (p.getAttribute("nr").equalsIgnoreCase(GameNr)) {
                try {
                    p.appendChild(boom);
                    boom.setAttribute("nr", Integer.toString(boomCurrent));

                } catch (Exception e) {
                    System.out.println("game Not found");
                }
            }
        }

        System.out.println(String.format("boom %d added", boomCurrent));
        boomCurrent++;
        try {
            save();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        try {
            String[] array = EndPoints.split("-");
            if (Integer.parseInt(array[0])> Integer.parseInt(array[1])) {
                updateEndScore("W");
            }
            else {
                updateEndScore("Z");
            }

        } catch (Exception e) {
            System.out.println("Boom saving error");
        }



    }
    /**
     * Updates the ranking in the file
     * Still W.I.P.
     */
    public void updatingRanking() {
        //players = {{ID,name},...}
        Node e=getNode("Trash");
        Element a2=(Element) getNode("Trash");
        NodeList gameList = doc.getElementsByTagName("game");
        System.out.println(String.format("Number of %d", gameList.getLength()));
        String [][] gameArray  = new String[gameList.getLength()][5];
        // getting nodelist games
        for (int i = 0; i < gameList.getLength(); i++) {
            Node game = gameList.item(i);
            for (int j = 0; j < game.getChildNodes().getLength(); j++) {
                Node a1 = game.getChildNodes().item(j);
                if (a1.getNodeType()==Node.ELEMENT_NODE) {
                    if (a1.getNodeName().equalsIgnoreCase("gameScore")) {
                        gameArray[i][4]=e.getTextContent();
                        System.out.println(String.format("%d %d: %s", i,4,a2.getTextContent()));
                    }
                    else if (a1.getNodeName().equalsIgnoreCase("W")) {
                        for (int k = 0; k < a1.getChildNodes().getLength(); k++) {
                            if (a1.getChildNodes().item(k).getNodeType()==Node.ELEMENT_NODE) {
                                a2= (Element) a1.getChildNodes().item(k);
                                gameArray[i][k]=a2.getTextContent();
                                System.out.println(String.format("%d %d: %s", i,k,a2.getTextContent()));
                            }
                        }
                    }
                    else if (a1.getNodeName().equalsIgnoreCase("Z")) {
                        for (int k = 0; k < a1.getChildNodes().getLength(); k++) {
                            if (a1.getChildNodes().item(k).getNodeType()==Node.ELEMENT_NODE) {
                                a2= (Element) a1.getChildNodes().item(k);
                                gameArray[i][k+2]=a2.getTextContent();
                                System.out.println(String.format("%d %d: %s", i,k+2,a2.getTextContent()));
                            }
                        }
                    }
                }

            }
			/*
			try {
				String winner = e.getTextContent();
				int w_score=Integer.parseInt(winner.split("-")[0]);
				int z_score=Integer.parseInt(winner.split("-")[1]);
				System.out.println(String.format("%d:%d", w_score,z_score));
			} catch (Exception e2) {

			}*/
        }
        display(gameArray);
        //end Looping games

        getAllInformation(false);//updating players
    }
    /**
     * Run after game saves the file and prints the winners
     * @param Who The winners of type String
     */
    private void updateEndScore(String Who) {
        System.out.println(Who);
        Node Endscore = getNode("endgame");
        NodeList all = doc.getElementsByTagName("game");
        for (int i = 0; i < all.getLength(); i++) {
            Element p = (Element) all.item(i);
            if (p.getAttribute("nr").equalsIgnoreCase(GameNr)) {
                NodeList all2 =p.getChildNodes();
                for (int j = 0; j < all2.getLength(); j++) {
                    if (all2.item(j).getNodeType()==Node.ELEMENT_NODE) {
                        if (all2.item(j).getNodeName().equalsIgnoreCase("gameScore")) {
                            System.out.println("End score: " + all2.item(j).getTextContent());
                            Endscore=all2.item(j);
                        }
                    }
                }
            }
        }
        //end looping
        try {
            String text = Endscore.getTextContent();
            int w_score=Integer.parseInt(text.split("-")[0]);
            int z_score=Integer.parseInt(text.split("-")[1]);

            if (Who.equalsIgnoreCase("W")) {
                w_score++;
            }
            else {
                z_score++;
            }
            Endscore.setTextContent(String.format("%d-%d", w_score,z_score));
            System.out.println(String.format("%d-%d", w_score,z_score));
            save();
        } catch (Exception e) {
            System.out.println("Failed");
        }

    }
    /**
     * Saves the '.xml' file
     * @throws TransformerException
     */
    private void save() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);

    }
}
