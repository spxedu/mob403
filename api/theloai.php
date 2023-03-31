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

//---- xử lý gọi hàm 

$method = $_SERVER['REQUEST_METHOD'];
if( $method == 'GET'){
    if(empty($_GET['id'])) // không có id là trang danh sách, có id là chi tiết
        listAll(); // gọi hàm listAll;
    // else{
            // gọi hàm xem chi tiết
    // }
}