package dbconnect;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class S3UtilTest {
    @Test
    public void testCreateFolder() {
        S3Util.setBucketName("photo-shoppii");
        assertTrue(S3Util.createFolder("test3"));
        fail("Unable to create new folder");
    }

    @Test
    public void testDeleteBucketObjects() {
        S3Util.setBucketName("photo-shoppii");
        assertTrue(S3Util.deleteBucketObjects("test3/"));
        fail("Unable to delete object.");
    }

    @Test
    public void testUploadObject() {
        // S3Util.setBucketName("photo-shoppii");
        // assertEquals(true, S3Util.uploadObject(objectName, inputStream));
    }

    @Test
    public void testGetObject() {
        S3Util.setBucketName("photo-shoppii");
        S3Util.createFolder("test3");
        assertArrayEquals(new String[0],
             S3Util.getObject("test3").toArray());
        fail("Unable to Get Object list");
        S3Util.deleteBucketObjects("test3/"); 
    }


}
