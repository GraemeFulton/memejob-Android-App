package com.memejob.app.util;

import com.memejob.app.data.RssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by graeme on 19/04/14.
 */
public class RssParseHandler extends DefaultHandler {

    StringBuilder sb;
    private List<RssItem> rssItems;

    // Used to reference item while parsing
    private RssItem currentItem;

    // Parsing title indicator
    private boolean parsingTitle;
    // Parsing link indicator
    private boolean parsingLink;

    //parse description indicator
    private boolean parsingDescription;

    public RssParseHandler() {
        rssItems = new ArrayList<RssItem>();
    }

    public List<RssItem> getItems() {
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RssItem();
        } else if ("title".equals(qName)) {
            parsingTitle = true;
        }
        else if ("description".equals(qName)) {
            parsingDescription = true;
        }
        else if ("link".equals(qName)) {
            sb = new StringBuilder();
            parsingLink = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;
        }
        else if ("description".equals(qName)) {
            parsingDescription = false;
        }
        else if ("link".equals(qName)) {
            parsingLink = false;
            //Log.i("LINk", sb.toString());

        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        }
        else if (parsingDescription) {
            if (currentItem != null)
                currentItem.setDescription(new String(ch, start, length));
        }

        else if (parsingLink) {
            if (currentItem != null) {
                sb.append(String.copyValueOf(ch, start, length));
                //currentItem.setLink(content);
               //parsingLink = false;
                currentItem.setLink(sb.toString());

                String[] mThumbIds = {
                        "http://www.wearev1.com/blog/wp-content/uploads/2013/08/3tfk7e.jpg",
                       "http://www.quickmeme.com/img/3c/3c0c8a1f529f7bc3014025d820b7273802fc01eca0ca741f3b323d50e7f1c0eb.jpg",
                        "https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-ash3/t1.0-9/1004996_474675415973997_546675782_n.jpg",
                        "http://www.quickmeme.com/img/52/5238110f8cf6eedf55bc5ac637231bfd7490675374dffac49d0eb758fe17a8b6.jpg",
                        "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSHe2GUGDF7acRCJGer0jyRGTon-7_IUtSMTb7t7srORwANh8E9BQ",
                        "http://www.seoreseller.com/blog/wp-content/uploads/2014/02/content-audit-meme.jpg",
                        "http://cdn.memegenerator.net/instances/45721638.jpg",
                        "http://datapigtechnologies.com/blog/wp-content/uploads/2014/04/041714_0620_ExcelMemeCo7.png"


                };
                currentItem.setImage(mThumbIds[new Random().nextInt(mThumbIds.length)]);

            }
        }

    }

}