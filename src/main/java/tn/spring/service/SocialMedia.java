package tn.spring.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SocialMedia {
    /* All Social Media Sites
     * -------------------------------------------------
     */

    /* All Social Media Sites ~ Nice Names
     * -------------------------------------------------
     */

    public Map<String, String> GetSocialMediaSites_NiceNames() {
        Map<String, String> socialmediasites = new HashMap<String, String>(){{
            put("add.this", "AddThis");
            put("blogger", "Blogger");
            put("buffer", "Buffer");
            put("diaspora", "Diaspora");
            put("douban", "Douban");
            put("email", "EMail");
            put("evernote", "EverNote");
            put("getpocket", "Pocket");
            put("facebook", "FaceBook");
            put("flattr", "Flattr");
            put("flipboard", "FlipBoard");
            put("google.bookmarks", "GoogleBookmarks");
            put("instapaper", "InstaPaper");
            put("line.me", "Line.me");
            put("linkedin", "LinkedIn");
            put("livejournal", "LiveJournal");
            put("gmail", "GMail");
            put("hacker.news", "HackerNews");
            put("ok.ru", "OK.ru");
            put("pinterest", "Pinterest");
            put("qzone", "QZone");
            put("reddit", "Reddit");
            put("renren", "RenRen");
            put("skype", "Skype");
            put("sms", "SMS");
            put("surfingbird.ru", "SurfingBird.ru");
            put("telegram.me", "Telegram.me");
            put("threema", "Threema");
            put("tumblr", "Tumblr");
            put("twitter", "Twitter");
            put("vk", "VK");
            put("weibo", "Weibo");
            put("whatsapp", "WhatsApp");
            put("xing", "Xing");
            put("yahoo", "Yahoo");
        }};

        return socialmediasites;
    }

    /* Social Media Sites With Share Links
     * -------------------------------------------------
     */

    public String[] GetSocialMediaSites_WithShareLinks_OrderedByPopularity() {
        return new String[]{
                "facebook",
                "whatsapp",
                "twitter",
                "linkedin",
                "gmail",
                "yahoo",
        };
    }

    public String[] GetSocialMediaSites_WithShareLinks_OrderedByAlphabet() {
        Map<String, String> socialmediaurls = GetSocialMediaSites_NiceNames();
        String[] socialmediasites = new String[socialmediaurls.size()];

        int index = 0;

        for(Map.Entry<String, String> entry : socialmediaurls.entrySet()) {
            String key = entry.getKey();
            socialmediasites[index++] = key;
        }

        Arrays.sort(socialmediasites);

        return socialmediasites;
    }

    /* Social Media Site Links With Share Links
     * -------------------------------------------------
     */

    public Map<String, String> GetSocialMediaSiteLinks_WithShareLinks(Map<String, String> args) {
        String[] validargs = {
                "url",
                "quote",
                "title",
                "image",
                "desc",
                "appid",
                "redirecturl",
                "via",
                "hashtags",
                "provider",
                "language",
                "userid",
                "category",
                "phonenumber",
                "emailaddress",
                "cemailaddress",
                "bccemailaddress",
        };

        for (String validarg : validargs) {
            if(args.get(validarg) == null) {
                args.put(validarg, "");
            }
        }

        String text = args.get("title");
        String desc = args.get("desc");

        if(desc != null) {
            text += "%20%3A%20";
            text += desc;
        }

        args.put("text", text);

        Map<String, String> socialmediasites = new HashMap<String, String>(){{
            put("facebook", "http://www.facebook.com/sharer.php?u=" + args.get("u")+ "&quote=" + args.get("&quote"));
            put("gmail", "https://mail.google.com/mail/?view=cm&su=" + args.get("title") + "&body=" + args.get("url"));
            put("linkedin", "https://www.linkedin.com/sharing/share-offsite/?url=" + args.get("url"));
            put("sms", "sms:" + args.get("phone_number") + "?body=" + args.get("text"));
            put("twitter", "https://twitter.com/intent/tweet?url=" + args.get("url") + "&text=" + args.get("text") + "&via=" + args.get("via") + "&hashtags=" + args.get("hash_tags"));
            put("whatsapp", "https://api.whatsapp.com/send?text=" + args.get("text") + "&url=" + args.get("url"));
            put("yahoo", "http://compose.mail.yahoo.com/?to=" + args.get("email_address") + "&subject=" + args.get("title") + "&body=" + args.get("text"));
        }};

        return socialmediasites;
    }



    public static String Getsharelink (String file, String cont) {
        SocialMedia sm = new SocialMedia();
        String cont2=cont.replace(" ","%20");
        Map<String, String> socialmediaargs = new HashMap<String, String>(){{
            put("u",file);
            put("url",file);
            put("text",cont2);
            put("desc", cont);
            put("&quote",cont2);
            put("title",cont2);
        }};
        Map<String, String> socialmediasites = sm.GetSocialMediaSiteLinks_WithShareLinks(socialmediaargs);

        //System.out.println(sm.GetSocialMediaSites_NiceNames().toString());

        //for (String socialmedia : sm.GetSocialMediaSites_WithShareLinks_OrderedByAlphabet()) {
        for (String socialmedia : sm.GetSocialMediaSites_WithShareLinks_OrderedByPopularity()) {
            System.out.println(socialmedia + " : " + socialmediasites.get(socialmedia));
        }
        //System.out.println(sm.GetSocialMediaSites_WithShareLinks_OrderedByPopularity());
        return "";
    }
}

