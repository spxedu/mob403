const mongoose = require('mongoose');
// mongoose.connect(); tham khảo bài trước copy chuỗi kết nối sang
mongoose.connect('mongodb+srv://sondt32:Abc_123456@cluster0.h0gfl.mongodb.net/demo_dangky_dangnhap?authSource=admin&replicaSet=atlas-5332zd-shard-0&readPreference=primary&appname=MongoDB%20Compass&ssl=true');
// định nghĩa cấu trúc cho collection users

const userSchema = new mongoose.Schema({
    username: 'String',
    passwd: 'String',
    email: 'String'
});
// export model
module.exports = mongoose.model('tb_user', userSchema );
