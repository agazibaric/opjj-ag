package gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.networking;

import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data.ImageResponse;
import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data.UserInfo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FetcherService {

    @GET("java/image.json")
    Call<ImageResponse> getImage();

    @GET("java/cap.json")
    Call<UserInfo> getUserInfo();

}
