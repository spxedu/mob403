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
       
## Viết hàm thực hiên GET ở trên activity

```
void DemoGETRetrofit (){
        // tạo đối tượng chuyển đổi GSON
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        // sử dụng Interface
        UserInterface userInterface = retrofit.create(UserInterface.class);
        // tạo call
        Call< List<UserDTO> > objCall = userInterface.lay_danh_sach();
        // gọi get:
        objCall.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, retrofit2.Response<List<UserDTO>> response) {

                if(response.isSuccessful()){
                    List<UserDTO> dsTK = response.body();
                    // bạn làm gì với cái list này thì tự bạn làm
                    Log.d("zzzzzz", "Ket qua: " + dsTK.size());

                }
                else{
                    Log.d("zzzz", "onResponse: lỗi " + response.errorBody() );
                }

            }
            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                Log.e("zzzzzzz", "onFailure: " + t.getMessage());
            }
        });

    }
```

## Viết hàm POST để thêm dữ liệu lên server 


```
void DemoPOSTRetrofit (){
        // tạo đuối tượng chuyển đổi
        Gson gson = new GsonBuilder().setLenient().create();
        // tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        // khởi tạo interface
        UserInterface userInterface = retrofit.create(UserInterface.class);

        // Tạo đối tượng DTO để gửi lên API
        UserDTO objU = new UserDTO();
        objU.setUsername("nva0001");
        objU.setEmail("nva001@gmail.com");

        // tạo obj call
        Call<UserDTO> objCall = userInterface.them_moi_user( objU );

        // Thực hiện gửi dữ liệu lên server
        objCall.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, retrofit2.Response<UserDTO> response) {
                if(response.isSuccessful()){
                    UserDTO obj = response.body();
                    Log.d("zzzz", "onResponse: Kết quả post: " + obj.getEmail());
                }else{
                    Log.d("zzzz", "onResponse: Lỗi " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Log.e("zzzz", "onFailure: " + t.getMessage() );
            }
        });
 
    }
    
    ```
