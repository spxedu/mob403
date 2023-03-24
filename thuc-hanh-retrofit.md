https://square.github.io/retrofit/ 
https://github.com/square/retrofit 

## Nhúng thư viện vào gradle:
```
implementation 'com.squareup.retrofit2:retrofit:2.9.0'

implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
```
Link API: https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users 
## 1. Tạo file DTO để chuyển đổi dữ liệu

```

public class UserDTO  {
    String id;
    String username;
    String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

```
## 2. Tạo  lớp interface để retrofit hiểu các hàm cần truy cập

```
public interface UserInterface {
    // get: lấy ds user
    // post: tạo mới
    // https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users
    @GET ("/users")  // ghép phần cuối của địa chỉ api là users
    Call<List<UserDTO>> lay_danh_sach(); // get trả về 1 danh sách

    @POST("/users")
    Call<UserDTO> them_moi_user (@Body UserDTO userDTO);  // thêm mới sẽ truyền vào 1 đối tượng

}
```
        
        
