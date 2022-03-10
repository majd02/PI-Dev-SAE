package tn.spring.service;

import com.microsoft.azure.cognitiveservices.vision.computervision.*;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// </snippet_imports>

// <snippet_classdef_1>
public class ImageAnalysisQuickstart {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageAnalysisQuickstart.class);

    static String subscriptionKey = "9224bdcff4494d2ca9a92a0cd7026b10";
    static String endpoint = "adampidev.cognitiveservices.azure.com";




    public static String GetAnalyze(String imageURL)  {
        System.out.println("\nAzure Cognitive Services Computer Vision - Java Quickstart Sample");

        // Create an authenticated Computer Vision client.
        ComputerVisionClient compVisClient = Authenticate(subscriptionKey, endpoint);

        // Analyze local and remote images
        String c= AnalyzeLocalImage(compVisClient,imageURL);
return c;
    }
    // </snippet_main>

    // <snippet_auth>
    public static ComputerVisionClient Authenticate(String subscriptionKey, String endpoint){
        return ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint);
    }
    // </snippet_auth>

    // <snippet_analyzelocal_refs>
    public static String AnalyzeLocalImage(ComputerVisionClient compVisClient, String imageURL) {
        /*
         * Analyze a local image:
         *
         * Set a string variable equal to the path of a local image. The image path
         * below is a relative path.
         */
        String pathToLocalImage = "src\\main\\resources\\myImage.png";
        // </snippet_analyzelocal_refs>

        // <snippet_analyzelocal_features>
        // This list defines the features to be extracted from the image.
        List<VisualFeatureTypes> featuresToExtractFromLocalImage = new ArrayList<>();
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.DESCRIPTION);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.CATEGORIES);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.TAGS);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.FACES);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.OBJECTS);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.BRANDS);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.ADULT);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.COLOR);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.IMAGE_TYPE);
        // </snippet_analyzelocal_features>

        System.out.println("\nAnalyzing local image ...");
        // <snippet_analyzelocal_analyze>

        Map<String,Double> output = new HashMap<>();
        try {
            // Need a byte array for analyzing a local image.
            File rawImage = new File(imageURL);
            byte[] imageByteArray = Files.readAllBytes(rawImage.toPath());

            // Call the Computer Vision service and tell it to analyze the loaded image.
            ImageAnalysis analysis = compVisClient.computerVision().analyzeImageInStream().withImage(imageByteArray)
                    .withVisualFeatures(featuresToExtractFromLocalImage).execute();



            // </snippet_analyzelocal_analyze>

            // <snippet_analyzelocal_captions>
            // Display image captions and confidence values.
            System.out.println("\nCaptions: ");
            for (ImageCaption caption : analysis.description().captions()) {
                System.out.printf("\'%s\' with confidence %f\n", caption.text(), caption.confidence());
            }
            // </snippet_analyzelocal_captions>

            // <snippet_analyzelocal_category>
            // Display image category names and confidence values.
            System.out.println("\nCategories: ");
            for (Category category : analysis.categories()) {
                System.out.printf("\'%s\' with confidence %f\n", category.name(), category.score());
               String output1= "Categorie "+category.name()+" with confidence";
                output.put(output1,category.score());
            }
            // </snippet_analyzelocal_category>

            // <snippet_analyzelocal_tags>
            // Display image tags and confidence values.
            System.out.println("\nTags: ");
            for (ImageTag tag : analysis.tags()) {
                System.out.printf("\'%s\' with confidence %f\n", tag.name(), tag.confidence());
                String str="Tags "+tag.name()+ " with confidence " ;
                output.put(str,tag.confidence());
            }
            // </snippet_analyzelocal_tags>

            // <snippet_analyzelocal_faces>
            // Display any faces found in the image and their location.
            System.out.println("\nFaces: ");
            for (FaceDescription face : analysis.faces()) {
                System.out.printf("\'%s\' of age %d at location (%d, %d), (%d, %d)\n", face.gender(), face.age(),
                        face.faceRectangle().left(), face.faceRectangle().top(),
                        face.faceRectangle().left() + face.faceRectangle().width(),
                        face.faceRectangle().top() + face.faceRectangle().height());
                //output.put(face.gender(),face.age());
            }
            // </snippet_analyzelocal_faces>

            // <snippet_analyzelocal_objects>
            // Display any objects found in the image.
            System.out.println("\nObjects: ");
            for (DetectedObject object : analysis.objects()) {
                System.out.printf("Object \'%s\' detected at location (%d, %d)\n", object.objectProperty(),
                        object.rectangle().x(), object.rectangle().y());
                String output2= "Objects: "+object.objectProperty();
                int a = Integer.parseInt( object.rectangle().x()+ "" + object.rectangle().y());
                Double a2= new Double(a);
                output.put(output2,a2);

            }
            // </snippet_analyzelocal_objects>

            // <snippet_analyzelocal_brands>
            // Display any brands found in the image.
            System.out.println("\nBrands: ");
            for (DetectedBrand brand : analysis.brands()) {
                System.out.printf("Brand \'%s\' detected at location (%d, %d)\n", brand.name(),
                        brand.rectangle().x(), brand.rectangle().y());
                String output3= "Brand: "+ brand.name();
                int a = Integer.parseInt(  brand.rectangle().x()+ "" + brand.rectangle().y());
                Double a2= new Double(a);
                output.put(output3,a2);
            }
            // </snippet_analyzelocal_brands>

            // <snippet_analyzelocal_adult>
            // Display whether any adult/racy/gory content was detected and the confidence
            // values.

            System.out.println("\nAdult: ");
            System.out.printf("Is adult content: %b with confidence %f\n", analysis.adult().isAdultContent(),
                    analysis.adult().adultScore());
            String str1 = new Boolean(analysis.adult().isAdultContent()).toString();
            String a= "Is adult content: "+str1+" with confidence ";
            output.put(a,analysis.adult().adultScore());
            System.out.printf("Has racy content: %b with confidence %f\n", analysis.adult().isRacyContent(),
                    analysis.adult().racyScore());
            String str2 = new Boolean(analysis.adult().isRacyContent()).toString();
            String a2= "Has racy content: "+str2+" with confidence ";
            output.put(a2,analysis.adult().racyScore());

            System.out.printf("Has gory content: %b with confidence %f\n", analysis.adult().isGoryContent(),
                    analysis.adult().goreScore());

            String str3 = new Boolean(analysis.adult().isGoryContent()).toString();
            String a3= "Has racy content: "+str3+" with confidence ";
            output.put(a3,analysis.adult().goreScore());




            // </snippet_analyzelocal_adult>

            // <snippet_analyzelocal_colors>
            // Display the image color scheme.
            System.out.println("\nColor scheme: ");
            System.out.println("Is black and white: " + analysis.color().isBWImg());
            System.out.println("Accent color: " + analysis.color().accentColor());
            System.out.println("Dominant background color: " + analysis.color().dominantColorBackground());
            System.out.println("Dominant foreground color: " + analysis.color().dominantColorForeground());
            System.out.println("Dominant colors: " + String.join(", ", analysis.color().dominantColors()));
            // </snippet_analyzelocal_colors>

            // <snippet_analyzelocal_celebrities>
            // Display any celebrities detected in the image and their locations.
            System.out.println("\nCelebrities: ");
            for (Category category : analysis.categories()) {
                if (category.detail() != null && category.detail().celebrities() != null) {
                    for (CelebritiesModel celeb : category.detail().celebrities()) {
                        System.out.printf("\'%s\' with confidence %f at location (%d, %d), (%d, %d)\n", celeb.name(),
                                celeb.confidence(), celeb.faceRectangle().left(), celeb.faceRectangle().top(),
                                celeb.faceRectangle().left() + celeb.faceRectangle().width(),
                                celeb.faceRectangle().top() + celeb.faceRectangle().height());
                    }
                }
            }
            // </snippet_analyzelocal_celebrities>

            // <snippet_analyzelocal_landmarks>
            // Display any landmarks detected in the image and their locations.
            System.out.println("\nLandmarks: ");
            for (Category category : analysis.categories()) {
                if (category.detail() != null && category.detail().landmarks() != null) {
                    for (LandmarksModel landmark : category.detail().landmarks()) {
                        System.out.printf("\'%s\' with confidence %f\n", landmark.name(), landmark.confidence());
                    }
                }
            }
            // </snippet_analyzelocal_landmarks>

            // <snippet_imagetype>
            // Display what type of clip art or line drawing the image is.
            System.out.println("\nImage type:");
            System.out.println("Clip art type: " + analysis.imageType().clipArtType());
            System.out.println("Line drawing type: " + analysis.imageType().lineDrawingType());
            // </snippet_imagetype>
            // <snippet_analyze_catch>
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
         String d = output.toString();
        return d;
    }
    // </snippet_analyze_catch>

    // END - Analyze a local image.

    // <snippet_analyzeurl>
  
    // END - Analyze an image from a URL.
    // </snippet_analyzeurl>

    // <snippet_classdef_2>
}