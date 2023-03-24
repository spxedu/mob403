package net.sdt.demovolleyretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInterface {
    // get: lấy ds user
    // post: tạo mới
    // https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users
    @GET ("/users")  // ghép phần cuối của địa chỉ api là users
    Call<List<UserDTO>> lay_danh_sach(); // get trả về 1 danh sách

    @POST("/users")
    Call<UserDTO> them_moi_user (@Body UserDTO userDTO);  // thêm mới sẽ truyền vào 1 đối tượng

}
