package poc.debnathsupriyo.avengersportal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.multipart.FormDataParam;

@Path("/profileAvatar")
public class ProfileAvatarController {
	
	
	@HEAD
	public Response pingWebhook() {
		
		return Response.status(Status.OK).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileAvatarBase64Encoded(@QueryParam("avatarId") String profileAvatarName) {
		
		String locationURI = "/customdir/avengersportal/avatars/";
		String base64EncodedImage = "";
		File profileAvatar = new File(locationURI+profileAvatarName);
		FileInputStream fileStream = null;
		
		try {
			fileStream = new FileInputStream(profileAvatar);
			byte[] buffer = new byte[(int) profileAvatar.length()];
			fileStream.read(buffer);
			base64EncodedImage = new String(Base64.encode(buffer), "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		JSONObject responseData = new JSONObject();
		responseData.put("AvatarName", base64EncodedImage);
		return Response.status(Status.OK).entity(responseData.toString()).build();
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveProfileAvatar(@FormDataParam("avatar") InputStream requestData, @FormDataParam("avatar") FormDataContentDisposition requestMetaData) {
		
		String locationURI = "/customdir/avengersportal/avatars/";
		
		String profileAvatarId = UUID.randomUUID().toString();
		String profileAvatarType = requestMetaData.getFileName().substring(requestMetaData.getFileName().lastIndexOf("."));
		String profileAvatarName = profileAvatarId + profileAvatarType;
		
		File profileAvatar = new File(locationURI+profileAvatarName);
		FileOutputStream fileStream = null;
		byte[] fileBuffer = new byte[1024];
		int _counter = 0;

		try {
			
			fileStream = new FileOutputStream(profileAvatar);
			while((_counter = requestData.read(fileBuffer)) != -1)
				fileStream.write(fileBuffer, 0, _counter);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} 
		
		JSONObject responseData = new JSONObject();
		responseData.put("AvatarName", profileAvatarName);
		return Response.status(Status.CREATED).entity(responseData.toString()).build();
		
		
	}
}
