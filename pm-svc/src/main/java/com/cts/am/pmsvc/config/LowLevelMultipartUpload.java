package com.cts.am.pmsvc.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;

public class LowLevelMultipartUpload {

	public static void main(String[] args) {
		Regions clientRegion = Regions.DEFAULT_REGION;
		String bucketName = "***Bucket name***";
		String keyName = "***key name***";
		String filePath = "*** path to file upload ***";
		
		File file = new File(filePath);
		long contentLenght = file.length();
		long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.
		
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withRegion(clientRegion)
					.withCredentials(new ProfileCredentialsProvider())
					.build();
			//Create a list of ETag objects. You retrive Etags for each object part
			// then, after each individual part has been uploaded, pass the list of ETags to]
			// the request to complete the upload
			List<PartETag> partETags = new ArrayList<PartETag>();
			
			//Initiate the multipart upload
			InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, keyName);
			InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);
			
			//Upload the file parts
			long filePosition = 0;
			for(int i = 1; filePosition < contentLenght; i++) {
				//Because the last part could be less than 5 MB, adjust the part size as needed
				partSize = Math.min(partSize, (contentLenght - filePosition));
				
				//Create the request to upload a part.
				UploadPartRequest uploadRequest = new UploadPartRequest()
						.withBucketName(bucketName)
						.withKey(keyName)
						.withUploadId(initResponse.getUploadId())
						.withPartNumber(i)
						.withFileOffset(filePosition)
						.withFile(file)
						.withPartSize(partSize);
				
				//Upload the part and add the response's ETag to our list.
				UploadPartResult uploadResult = s3Client.uploadPart(uploadRequest);
				partETags.add(uploadResult.getPartETag());
				
				filePosition += partSize;
			}
			//Complete the multipart upload
			CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, keyName, initResponse.getUploadId(), partETags);
			s3Client.completeMultipartUpload(compRequest);
		} catch(AmazonServiceException e) {
			e.printStackTrace();
		}catch(SdkClientException e) {
			e.printStackTrace();
		}
	}
}
