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

// phần dưới dành cho HTML
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    <h1>Danh sách thể loại</h1>
    <table border="1">

    <tr>
        <th>ID</th>   
        <th>Tên loại</th>
    </tr>

    <?php  
        // trong này là phạm vi của PHP
        foreach( $danh_sach as $k => $row){

                echo "
                        <tr>
                            <td>  {$row[ 'id'] }   </td>
                            <td>   {$row[ 'ten_loai'] }   </td>
                        </tr>            
                        ";

        }
    ?>

    </table>
</body>
</html>