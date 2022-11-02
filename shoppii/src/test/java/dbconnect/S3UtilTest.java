package dbconnect;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class S3UtilTest {
    @Test
    public void testCreateFolder() {
        S3Util.setBucketName("photo-shoppii");
        try {
            assertTrue(S3Util.createFolder("test3"));
        } catch (AssertionFailedError e) {
            fail("Unable to create new folder");
        }    
    }

    @Test
    public void testDeleteBucketObjects() {
        S3Util.setBucketName("photo-shoppii");
        try {
            assertTrue(S3Util.deleteBucketObject("test3/"));
        } catch (AssertionFailedError e) {
            fail("Unable to delete object.");
        }
        
        
    }

    @Test
    public void testUploadObject() {
        // S3Util.setBucketName("photo-shoppii");
        // assertEquals(true, S3Util.uploadObject(objectName, inputStream));
    }

    @Test
    public void testGetObject() {
        S3Util.setBucketName("photo-shoppii");
        
        try {
            S3Util.createFolder("test3");
            assertArrayEquals(new String[0],
             S3Util.getObject("test3").toArray());
             S3Util.deleteBucketObject("test3/"); 
        } catch (Exception e) {
            fail("Unable to Get Object list");
        } 
    }


}
