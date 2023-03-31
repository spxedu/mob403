<?php
// ?res=theloai  --> danh sách thể loại
// ?res=theloai&id=xxx ==> xem chi tiết 1 thể loại
function listAll(){
    global $objConn;
    try {
        $sql_str = "SELECT * FROM `the_loai`";
        // tạo đối tượng prepare chuẩn bị cho cú pháp thực thi truy vấn
        $stmt = $objConn->prepare(  $sql_str );
        // thực thi câu lệnh
        $stmt->execute(); 
        //thiết lập chế độ lấy dữ liệu
        $stmt->setFetchMode(PDO::FETCH_ASSOC);
    
        // lấy dữ liệu:
        $danh_sach = $stmt->fetchAll();

        $dataRes = [
            'status'=> 1,
            'msg'=> 'Thành công',
            'data'=> $danh_sach
        ];
        
         die(   json_encode($dataRes) );  
         
    } catch (Exception $e) {
         die( 'Lỗi thực hiện truy vấn CSLD ' . $e->getMessage()  );
    }

}

function addTL(){
    global $objConn;

    $ten_loai = $_POST['ten_loai'];
        if(empty ($ten_loai) ){
            $dataRes =[
                'status'=>0,
                'msg'=> 'Chưa nhập tên loại'
            ]
  
        }else{
            // đã nhập tên loại rồi ==> lưu vào CSDL
            try {

                $stmt =  $objConn->prepare(
                    "INSERT INTO the_loai (ten_loai) VALUES (:tham_so_ten);");
                
                // gán tham số cho câu lệnh
                $stmt->bindParam(":tham_so_ten", $ten_loai );
                // thực thi
                $stmt->execute();
 
                $dataRes =[
                    'status'=>1,
                    'msg'=>  'Đã thêm thành công'
                ]
            
            } catch (PDOException $e) {
                 
                $dataRes =[
                    'status'=>0,
                    'msg'=> 'Lỗi '. $e->getMessage()
                ]
            }
        }

        die(json_encode ($dataRes ));
}




//---- xử lý gọi hàm 

$method = $_SERVER['REQUEST_METHOD'];
if( $method == 'GET'){
    if(empty($_GET['id'])) // không có id là trang danh sách, có id là chi tiết
        listAll(); // gọi hàm listAll;
    // else{
            // gọi hàm xem chi tiết
    // }
}

if($method =='POST'){ // đã là post thì chỉ thêm mới, muốn sửa thì dùng PUT
    addTL();
}