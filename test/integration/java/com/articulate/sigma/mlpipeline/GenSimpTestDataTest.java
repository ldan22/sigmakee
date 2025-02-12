package com.articulate.sigma.mlpipeline;

import com.articulate.sigma.KBmanager;
import com.articulate.sigma.nlg.LanguageFormatter;
import com.articulate.sigma.utils.AVPair;
import com.articulate.sigma.wordNet.WordNetUtilities;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.articulate.sigma.mlpipeline.GenSimpTestData.*;

public class GenSimpTestDataTest {

    public static LFeatures lfeat = null;
    public static GenSimpTestData gstd = new GenSimpTestData();

    /** *************************************************************
     */
    @BeforeClass
    public static void init() {

        System.out.println("GenSimpTestDataTest.init()");
        KBmanager.getMgr().initializeOnce();
        kb = KBmanager.getMgr().getKB(KBmanager.getMgr().getPref("sumokbname"));
        lfeat = new LFeatures(gstd);
    }

    /** ***************************************************************
     */
    public void testVerb(String term, boolean negated, int tense, String word,
                         boolean plural, String expected, LFeatures lfeat) {

        System.out.println("======================= ");
        GenSimpTestData gstd = new GenSimpTestData();
        StringBuffer english = new StringBuffer();
        String v = gstd.verbForm(term,negated, tense, word, plural, english, lfeat);
        System.out.println("testVerb(): verb form: " + v);
        if (!v.equalsIgnoreCase(expected)) {
            if (!v.replace("won't","will not").equalsIgnoreCase(expected)) {
                System.out.println("Error!: found " + v + " Expected: " + expected);
                assertTrue(v.equalsIgnoreCase(expected));
            }
        }
    }

    /** ***************************************************************
     */
    public void testNoun(String term, String word,
                         boolean plural, String expected, LFeatures lfeat) {

        System.out.println("======================= ");
        GenSimpTestData gstd = new GenSimpTestData();
        StringBuffer english = new StringBuffer();
        AVPair avp = new AVPair();
        String v = gstd.nounFormFromTerm(term,avp);
        System.out.println("testVerb(): verb form: " + v);
        if (!v.equalsIgnoreCase(expected)) {
            if (!v.replace("won't","will not").equalsIgnoreCase(expected)) {
                System.out.println("Error!: found " + v + " Expected: " + expected);
                assertTrue(v.equalsIgnoreCase(expected));
            }
        }
    }
    /** ***************************************************************
     */
    @Test
    public void testVerbs() {

        System.out.println("GenSimpTestData.testVerbs()");
        String t = "Trespassing";
        ArrayList<String> synsets = WordNetUtilities.getEquivalentVerbSynsetsFromSUMO(t);
        System.out.println("testVerb(): equiv synsets size: " + synsets.size() + " for term: " + t);
        synsets = WordNetUtilities.getVerbSynsetsFromSUMO(t);
        System.out.println("testVerb(): synsets size: " + synsets.size() + " for term: " + t);
    }

    /** ***************************************************************
     */
    @Test public void testTrespass() { testVerb("Trespassing", false, PRESENT, "trespass", false, "Trespasses", lfeat); }
    @Test public void testTrespass2() { testVerb("Trespassing", false, PROGRESSIVE, "trespass", false, "is trespassing", lfeat); }
    @Test public void testBuying() {  testVerb("Buying",false, PRESENT, "buy", false, "Buys", lfeat);}
    @Test public void testBuying2() { testVerb("Buying",false, PAST, "buy", false, "Bought", lfeat);}
    @Test public void testWalking() { testVerb("Walking",false, PAST, "pad", false, "Padded", lfeat);}
    @Test public void testRequesting() { testVerb("Requesting",false, PRESENT, "wish", false, "Wishes", lfeat);}

    @Test public void testProcessYou() { lfeat.subj = "You";
        testVerb("Process",false, PRESENT, "process", false, "Process", lfeat);
        lfeat.subj = ""; }

    @Test public void testProcess() { lfeat.subj = "";
        testVerb("Process",false, PRESENT, "process", false, "Processes", lfeat);}
    @Test public void testLooking() { testVerb("Looking",false, PROGRESSIVE, "catch", false, "Is catching", lfeat);}
    @Test public void testSoccer() { testVerb("Soccer",false, PROGRESSIVE, "soccer", false, "Is playing soccer", lfeat);}

    @Test public void testListening() { testVerb("Listening",false, PAST, "hear", false, "Heard", lfeat);}
    @Test public void testApologizing() { testVerb("Apologizing",false, PROGRESSIVE, "apologize", false, "Is apologizing", lfeat);}
    @Test public void testIntentional() { testVerb("IntentionalProcess",false, PAST, "proceed", false, "Proceeded", lfeat);}
    @Test public void testSeeing() { testVerb("Seeing",false, PRESENT, "watch", false, "Watches", lfeat);}
    @Test public void testBegin() { testVerb("Process",false, PROGRESSIVE, "begin", false, "Is beginning", lfeat);}
    @Test public void testBegun() { testVerb("Process",false, PAST, "begin", false, "Begun", lfeat);}
    @Test public void testDistill() { testVerb("Distilling",false, PRESENT, "distill", false, "Distills", lfeat);}
    @Test public void testPunching() { testVerb("Punching",false, PRESENT, "punch", false, "Punches", lfeat);}
    @Test public void testGame() { testVerb("Game",false, PRESENT, "play", false, "Plays", lfeat);}
    @Test public void testFreezing() { testVerb("Freezing",false, PAST, "freeze", false, "Froze", lfeat);}
        // TODO: need exception to make this "John has frozen" rather than "John frozen"

    @Test public void testDivide() { testVerb("Separating",false, NOTIME, "divide", true, "Divides", lfeat);}
    @Test public void testBeckon() { testVerb("Waving",true, FUTUREPROG, "beckon", true, "will not be beckoning", lfeat);}
    @Test public void testHear() { testVerb("Hearing",false, PASTPROG, "hear", true, "were hearing", lfeat);}
    @Test public void testMelt() { testVerb("Melting",false, PASTPROG, "melt", true, "were melting", lfeat);}
    @Test public void testTaking() { testVerb("Driving",false, FUTURE, "take", true, "will take", lfeat);}
    @Test public void testWind() { testVerb("Wind",true, FUTURE, "blow", false, "will not blow", lfeat);}
    @Test public void testInterp() { testVerb("Interpreting",false, PROGRESSIVE, "rede", false, "is reding", lfeat);}

    /** ***************************************************************
     */
    @Test
    public void testNLG() {

        System.out.println("GenSimpTestData.testNLG()");
        String s = "(=> (and (valence ?REL ?NUMBER) (instance ?REL Predicate)) (forall (@ROW) (=> (?REL @ROW) (equal (ListLengthFn (ListFn @ROW)) ?NUMBER))))";
        String actual = toEnglish(s);
        System.out.println("Strike:" + kb.getTermFormat("EnglishLanguage","BaseballStrike"));
        System.out.println("Strike2:" + kb.getTermFormatMap("EnglishLanguage").get("BaseballStrike"));
        System.out.println("Strike3:" + LanguageFormatter.translateWord(kb.getTermFormatMap("EnglishLanguage"),"BaseballStrike"));
        System.out.println(actual);
    }

    /** ***************************************************************
     */
    @Test
    public void testLFeatures() {

        System.out.println("GenSimpTestData.testLFeatures()");
        //System.out.println("testLFeatures(): objects: " + lfeat.objects.terms);
    }

    /** ***************************************************************
     */
    @Test
    public void testGiving() {

        System.out.println("GenSimpTestData.testGiving()");
        String t = "Giving";
        ArrayList<String> synsets = WordNetUtilities.getEquivalentVerbSynsetsFromSUMO(t);
        System.out.println("testGiving(): equiv synsets size: " + synsets.size() + " for term: " + t);
        synsets = WordNetUtilities.getVerbSynsetsFromSUMO(t);
        System.out.println("testGiving(): synsets size: " + synsets.size() + " for term: " + t);
    }

    /** ***************************************************************
     */
    @Test
    public void testToy() {

        System.out.println("GenSimpTestData.testToy()");
        String word = "toy";
        int time = PAST;
        StringBuffer english = new StringBuffer();
        GenSimpTestData gstd = new GenSimpTestData();
        System.out.println("testToy(): past tense Game/toy: " + gstd.verbForm("Game",false,time,word,false, english,lfeat));
        word = "soccer";
        System.out.println("testToy(): past tense Soccer/soccer: " + gstd.verbForm("Soccer",false,time,word,false, english,lfeat));
        time = PRESENT;
        System.out.println("testToy(): present tense Game/toy: " + gstd.verbForm("Game",false,time,word,false, english,lfeat));
        word = "soccer";
        System.out.println("testToy(): present tense Soccer/soccer: " + gstd.verbForm("Soccer",false,time,word,false, english,lfeat));
        word = "walking";
        System.out.println("testToy(): present tense Walking/walking: " + gstd.verbForm("Walking",false,time,word,false, english,lfeat));
    }

    /** ***************************************************************
     */
    @Test
    public void testPutting() {

        System.out.println("GenSimpTestData.testPutting()");
        String t = "Putting";
        ArrayList<String> synsets = WordNetUtilities.getEquivalentVerbSynsetsFromSUMO(t);
        System.out.println("testPutting(): equiv synsets size: " + synsets.size() + " for term: " + t);

        synsets = WordNetUtilities.getVerbSynsetsFromSUMO(t);
        System.out.println("testPutting(): synsets size: " + synsets.size() + " for term: " + t);
    }

    /** ***************************************************************
     */
    @Test
    public void testTypes() {

        System.out.println("GenSimpTestData.testTypes()");
        String t = "Object";
        HashSet<String> hinsts = kb.kbCache.getInstancesForType(t);
        //System.out.println(hinsts);
        System.out.println("Object has instance BinaryPredicate: " + hinsts.contains("BinaryPredicate"));
        System.out.println("signature of 'half': " + kb.kbCache.getSignature("half"));
    }

}
