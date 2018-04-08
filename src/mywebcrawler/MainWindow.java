package mywebcrawler;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainWindow 
{
    static JFrame f;
    private int count=1;
    private static final String[] MAX_URLS = {"50", "100", "500", "1000"};
    private boolean crawling;
    private JTextField startURLField;
    private JTextField logFileField;
    private JComboBox maxURLCombo;
    private JTextField searchStringField;
    private JLabel crawlingResultLabel;
    private JCheckBox caseSensitiveBox;
    private JLabel crawledURLResultLabel;
    private JLabel urlToCrawlResultLabel;
    private JProgressBar crawlingProgress;
    private JLabel searchMatchesResultLabel;
    private JButton searchButton;
    private JTextArea urlTextArea;
    private PrintWriter logFileWriter;
    
    MainWindow()
    {
        f=new JFrame();
    }
    
    public void createWindow()
    {
        InitFrame.initialiseFrame(f, true, new Dimension(700, 650), "Web Crawler" , false, JFrame.EXIT_ON_CLOSE);
        JPanel panel = createPanel();
        createGUI(panel);
    }
    
    public JPanel createPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(217,206,186));
        return panel;
    }
    
    public void createGUI(JPanel panel)
    {
        JLabel startURLLabel = Labels.createLabel("Start URL:", BoundsResolution.getBRInstance(85, 20, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(startURLLabel);
        
        startURLField = TextFields.createTextField(BoundsResolution.getBRInstance(180, 15, 150, 20), "Century Schoolbook", Font.PLAIN, 13, 400, 30);
        panel.add(startURLField);
        
        JLabel maxURLLabel = Labels.createLabel("Max URLs to Crawl:", BoundsResolution.getBRInstance(15, 50, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(maxURLLabel);
        
        maxURLCombo = new JComboBox(MAX_URLS);
        maxURLCombo.setBounds(180, 45, 50, 20);
        maxURLCombo.setEditable(true);
        maxURLCombo.setSize(70, 30);
        panel.add(maxURLCombo);
        
        JLabel logFileLabel = Labels.createLabel("Log File:", BoundsResolution.getBRInstance(97, 85, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(logFileLabel);
        
        logFileField = TextFields.createTextField(BoundsResolution.getBRInstance(180, 80, 150, 20), "Century Schoolbook", Font.PLAIN, 13, 400, 30);
        String file = System.getProperty("user.dir") +
        System.getProperty("file.separator") + "crawler.log";
        logFileField.setText(file);
        panel.add(logFileField);
        
        JLabel searchStringLabel = Labels.createLabel("Search String:", BoundsResolution.getBRInstance(55, 120, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(searchStringLabel);
        
        searchStringField = TextFields.createTextField(BoundsResolution.getBRInstance(180, 115, 150, 20), "Century Schoolbook", Font.PLAIN, 13, 300, 30);
        panel.add(searchStringField);
        
        caseSensitiveBox=new JCheckBox("Case Sensitive");
        caseSensitiveBox.setFont(new Font("CenturySchoolbook", Font.BOLD, 13));
        caseSensitiveBox.setBounds(510, 120, 150, 20);
        panel.add(caseSensitiveBox);

        searchButton = new JButton("Search");
        searchButton.setBounds(280, 150, 150, 30);
        panel.add(searchButton);
        
        searchButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                urlActionSearch();
            }
        });
        
        JSeparator separator1=new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setBounds(0, 200, 800, 5);
        panel.add(separator1);
        
        JLabel crawlingLabel = Labels.createLabel("Crawling: ", BoundsResolution.getBRInstance(97, 220, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(crawlingLabel);
        
        crawlingResultLabel = Labels.createLabel("ab", BoundsResolution.getBRInstance(180, 220, 350, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(crawlingResultLabel);
        
        JLabel crawledURLLabel = Labels.createLabel("Crawled URLs: ", BoundsResolution.getBRInstance(60, 250, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(crawledURLLabel);
        
        crawledURLResultLabel = Labels.createLabel("ab", BoundsResolution.getBRInstance(180, 250, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(crawledURLResultLabel);
        
        JLabel urlToCrawlLabel = Labels.createLabel("URLs To Crawl: ", BoundsResolution.getBRInstance(53, 280, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(urlToCrawlLabel);
        
        urlToCrawlResultLabel = Labels.createLabel("URL ", BoundsResolution.getBRInstance(180, 280, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(urlToCrawlResultLabel);
        
        JLabel crawlingProgressLabel = Labels.createLabel("Crawling Progress: ", BoundsResolution.getBRInstance(28, 310, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(crawlingProgressLabel);
        
        crawlingProgress = new JProgressBar();
        crawlingProgress.setBounds(180, 305, 150, 20);
        crawlingProgress.setSize(400, 30);
        panel.add(crawlingProgress);
        
        JLabel searchMatchesLabel = Labels.createLabel("Search Matches: ", BoundsResolution.getBRInstance(48, 340, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(searchMatchesLabel);
        
        searchMatchesResultLabel = Labels.createLabel("Search ", BoundsResolution.getBRInstance(180, 340, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(searchMatchesResultLabel);
        
        JSeparator separator2=new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setBounds(0, 370, 800, 5);
        panel.add(separator2);
        
        JLabel urlHeadingLabel = Labels.createLabel("URL", BoundsResolution.getBRInstance(300, 380, 150, 20), "Century Schoolbook", Font.BOLD, 14);
        panel.add(urlHeadingLabel);
        
        urlTextArea = new JTextArea();
        urlTextArea.setEditable(false);
        //urlTextArea.setText("abc");
        //area.append("\n");
        //area.append("xyz");
        urlTextArea.setBounds(50,380,500,200);
        JScrollPane textAreaScrollPane= new JScrollPane(urlTextArea);
        textAreaScrollPane.setBounds(50, 410, 600, 200);
        
        panel.add(textAreaScrollPane);
        f.add(panel);
        f.pack();
    }
    
    private void urlActionSearch()
    {
        if (crawling) 
        {
            crawling = false;
            return;
        }
        urlTextArea.setText("");
        ArrayList<String> errors = new ArrayList<String>();
        String startURL = startURLField.getText().trim();
        if(startURL.length()<1)
            errors.add("Missing Start URL");
        else if (verifyURL(startURL) == null)
            errors.add("Invalid Start URL.");
        
        int maxURLs=0;
        String max = ((String) maxURLCombo.getSelectedItem()).trim();
        if (max.length() > 0) 
        {
            try 
            {
                maxURLs = Integer.parseInt(max);
            } 
            catch (NumberFormatException e) 
            {
            }
            if (maxURLs < 1)
            errors.add("Invalid Max URLs value.");
        }
        
        String logFile = logFileField.getText().trim();
        if (logFile.length() < 1)
            errors.add("Missing Matches Log File.");

        // Validate that search string has been entered.
        String searchString = searchStringField.getText().trim();
        if (searchString.length() < 1)
            errors.add("Missing Search String.");
        
        if (errors.size() > 0) 
        {
            StringBuffer message = new StringBuffer();
            
            // Concatenate errors into single message.
            for (int i = 0; i < errors.size(); i++) 
            {
                message.append(errors.get(i));
                if (i + 1 < errors.size())
                    message.append("\n");
            }
            showError(message.toString());
        }
        
        String filteredURL = removeWWWFromURL(startURLField.getText());
        //JOptionPane.showMessageDialog(f, filteredURL);

        search(logFile, startURL, maxURLs, searchString);
    }
    
    // Show dialog box with error message.
    private void showError(String message) 
    {
        JOptionPane.showMessageDialog(f, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private String removeWWWFromURL(String url)
    {
        int index = url.indexOf("://www.");
        if(index!=-1)
        {
            return url.substring(0, index+3) + url.substring(index+7);
        }
        
        return url;
    }
    
    private void search(final String logFile, final String startURL, final int maxURLs,final String searchString)
    {
        Thread t = new Thread (new Runnable()
        {
          @Override
          public void run()
          {
              f.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
              searchButton.setText("Stop");
              updateStats(startURL, 0, 0, maxURLs);
              try 
              {
                logFileWriter = new PrintWriter(new FileWriter(logFile));
              } 
              catch (Exception e) 
              {
                  System.out.println("Unable to open mentioned file");
              }
              crawling = true;
              crawl(startURL, maxURLs, searchString, caseSensitiveBox.isSelected());
              crawling = false;
              try 
              {
                logFileWriter.close();
              } 
              catch (Exception e) 
              {
                System.out.println("Unable to close file");
              }
              crawlingResultLabel.setText("Done");
              searchButton.setText("Search");
              f.setCursor(Cursor.getDefaultCursor());
          }
        }
        );
        t.start();
    }
    
    private void updateStats(String crawling, int crawled, int toCrawl, int maxURLs)
    {
        crawlingResultLabel.setText(crawling);
        crawledURLResultLabel.setText(""+crawled);
        urlToCrawlResultLabel.setText("" + toCrawl);
        
        // Update progress bar.
        if (maxURLs == -1) 
            crawlingProgress.setMaximum(crawled + toCrawl);
        else 
            crawlingProgress.setMaximum(maxURLs);
        
        crawlingProgress.setValue(crawled);
    }
    
    private void crawl(String startURL, int maxURLs, String searchString, boolean caseCheck)
    {
        HashSet<String> crawledList = new HashSet<String>();
        LinkedHashSet<String> toCrawlList = new LinkedHashSet<String>();
        
        //Add Start URL to URL crawling list.
        toCrawlList.add(startURL);
        while(crawling && toCrawlList.size()>0)
        {
            if(crawledList.size() == maxURLs)
                break;
            
            String url = (String)toCrawlList.iterator().next();
            toCrawlList.remove(url);
            
            //Convert string URL to URL object.
            URL verifiedURL = verifyURL(url);
            
            updateStats(url, crawledList.size(), toCrawlList.size(), maxURLs);
            
            crawledList.add(url);
            
            //Dowload page of the specifed URL.
            String pageContents = downloadPage(verifiedURL);
            
            if (pageContents != null && pageContents.length() > 0)
            {
                // Retrieve list of valid links from page.
                ArrayList links = retrieveLinks(verifiedURL, pageContents, crawledList);
                System.out.println(links);
                // Add links to the To Crawl list..
                toCrawlList.addAll(links);
                if (searchStringMatches(pageContents, searchString, caseCheck))
                    addMatch(url);
            }
            // Update crawling stats.
            updateStats(url, crawledList.size(), toCrawlList.size(), maxURLs);

        }
    }
        
    
    private URL verifyURL(String url)
    {
        //Allow URL which starts with http only
        if(!url.toLowerCase().startsWith("http://"))
            return null;
        URL verifiedURL = null;
        try
        {
            verifiedURL = new URL(url);
            return verifiedURL;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    private String downloadPage(URL verifiedURL)
    {
        try
        {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(verifiedURL.openStream()));
            String line;
            StringBuffer pageBuffer = new StringBuffer();
            while((line = bReader.readLine())!=null)
                pageBuffer.append(line);
            
            return pageBuffer.toString();
        }
        catch(Exception e)
        {
            
        }
        return null;
    }
    
    private ArrayList retrieveLinks(URL pageUrl, String pageContents, HashSet crawledList)
    {
        
        // Compile link matching pattern.
        Pattern p = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(pageContents);
        
        // Create list of link matches.
        ArrayList linkList = new ArrayList();
        while (m.find()) 
        {
            String link = m.group(1).trim();
            // Skip empty links.
            if (link.length() < 1) 
                continue;

            // Skip links that are just page anchors.
            if (link.charAt(0) == '#') 
                continue;

            // Skip mailto links.
            if (link.indexOf("mailto:") != -1)
                continue;

            // Skip JavaScript links.
            if (link.toLowerCase().indexOf("javascript") != -1)
                continue;

            // Prefix absolute and relative URLs if necessary.
            if (link.indexOf("://") == -1) 
            {
                // Handle absolute URLs.
                if (link.charAt(0) == '/') 
                    link = "http://" + pageUrl.getHost() + link;
                    // Handle relative URLs.
                else 
                {
                    String file = pageUrl.getFile();
                    if (file.indexOf('/') == -1) 
                        link = "http://" + pageUrl.getHost() + "/" + link; 
                    else 
                    {
                        String path = file.substring(0, file.lastIndexOf('/') + 1);
                        link = "http://" + pageUrl.getHost() + path + link;
                    }
                }
            }
            // Remove anchors from link.
            int index = link.indexOf('#');
            if (index != -1) 
                link = link.substring(0, index);

            // Remove leading "www" from URL's host if present.
            link = removeWWWFromURL(link);

            // Verify link and skip if invalid.
            URL verifiedLink = verifyURL(link);
            if (verifiedLink == null)
                continue;

            // Skip link if it has already been crawled.
            if (crawledList.contains(link))
                continue;

            // Add link to list.
            linkList.add(link);
        }
        return (linkList);
    }
    
    private boolean searchStringMatches(String pageContents, String searchString, boolean caseSensitive)
    {

        String searchContents = pageContents;
        if (!caseSensitive) 
            searchContents = pageContents.toLowerCase();
        
        // Split search string into individual terms.
        Pattern p = Pattern.compile("[\\s]+");
        String[] terms = p.split(searchString);

        for (int i = 0; i < terms.length; i++) 
        {
            if (caseSensitive) 
            {
                if (searchContents.indexOf(terms[i]) == -1) 
                    return false;
            } 
            else 
            {
                if (searchContents.indexOf(terms[i].toLowerCase()) == -1) 
                    return false;
            }
        }
        return true;
    }
    
    private void addMatch(String url) 
    {
        urlTextArea.append(url+ "\n");
        searchMatchesResultLabel.setText(((Integer)count++).toString());
        
        try 
        {
            logFileWriter.println(url);
        } 
        catch (Exception e) 
        {
            System.out.println("Unable to log match.");
        }
    }
}
