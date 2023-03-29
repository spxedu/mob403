<?php
// lệnh nhúng code từ file khác
require_once 'db.php';


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

    // in thử ra màn hình
    echo '<pre>';
    print_r ($danh_sach);
    echo '</pre>';

} catch (Exception $e) {
     die( 'Lỗi thực hiện truy vấn CSLD ' . $e->getMessage()  );
}


