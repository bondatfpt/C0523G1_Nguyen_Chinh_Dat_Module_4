// Kiểm tra định dạng số cmnd,cccd, số điện thoại, email,...:
// khi chưa nhập xong(hoặc sai định dạng) mà blur ra ngoài thì báo lỗi,
// nhưng khi focus lại vào thẻ input thì xóa lỗi,
// sai định dạng thì không cho submit.

// A. LOGIN & SIGN-UP
// 1. Số cmnd,cccd;
function validateIdentityNumber(){
    let identityNumber = document.getElementById("identityNumber").value;
    let identityNumberFormatError = document.getElementById("identityNumberFormatError");
    if (!/^\d{10,12}$/.test(identityNumber)){
        identityNumberFormatError.innerText = "Số CMND/CCCD không hợp lệ!";
        document.getElementById("submitButton").disabled = true;
    }
    else {
        identityNumberFormatError.innerText = "";
        document.getElementById("submitButton").disabled = false;
    }
}
function clearErrorIdentityNumber (){
    let identityNumberError = document.getElementById("identityNumberFormatError");
    identityNumberError.innerText = "";
}
function validateUserName(){
    let userName = document.getElementById("userName").value;
    let userNameFormatError = document.getElementById("userNameFormatError");
    if (!/^[A-z_](\w|\.|_){5,32}$/.test(userName)){
        userNameFormatError.innerText = "Tên tài khoản không chứa kí tự đặc biệt,bắt đầu bằng chữ !";
        document.getElementById("submitButton").disabled = true;
    }
    else {
       userNameFormatError.innerText = "";
       document.getElementById("submitButton").disabled = false;
    }
}
function clearErrorUserName (){
    let userNameError = document.getElementById("userNameFormatError");
    userNameError.innerText = "";
}

// 2.Email
function validateEmail(){
    let email = document.getElementById("email").value;
    let emailFormatError = document.getElementById("emailFormatError");
    if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)){
        emailFormatError.innerText = "Email sai định dạng!";
        document.getElementById("submitButton").disabled = true;
    }
    else {
        emailFormatError.innerText = "";
        document.getElementById("submitButton").disabled = false;
    }
}
function clearErrorEmail (){
    let emailFormatError = document.getElementById("emailFormatError");
    emailFormatError.innerText = "";
}

//3. Số điện thoại
function validatePhoneNumber(){
    let phoneNumber = document.getElementById("phoneNumber").value;
    let phoneNumberFormatError = document.getElementById("phoneNumberFormatError");
    if (!/^(84|0)([0-9]{7,})$/.test(phoneNumber)){
        phoneNumberFormatError.innerText = "Số điện thoại bắt đầu bằng số 0 hoặc 84, tối thiểu 8 kí tự";
        document.getElementById("submitButton").disabled = true;
    }
    else {
        phoneNumberFormatError.innerText = "";
        document.getElementById("submitButton").disabled = false;
    }
}
function clearErrorPhoneNumber (){
    let phoneNumberError = document.getElementById("phoneNumberFormatError");
    phoneNumberError.innerText = "";
}

// 4. Mật khẩu
function validatePassword(){
    let password = document.getElementById("password").value;
    let passwordFormatError = document.getElementById("passwordFormatError");
    if (!/^(?=.*[a-zA-Z])(?=.*\d).{8,}$/.test(password)){
        passwordFormatError.innerText = "Mật khẩu ít nhất 8 kí tự. Nên bao gồm số, chữ, kí tự đặc biệt!";
        document.getElementById("submitButton").disabled = true;
    }
    else {
        passwordFormatError.innerText = "";
        document.getElementById("submitButton").disabled = false;
    }
}
function clearErrorPassword (){
    let passwordFormatError = document.getElementById("passwordFormatError");
    passwordFormatError.innerText = "";
}

//5. Confirm mật khẩu
function validatePasswordConfirm(){
    let password = document.getElementById("password").value;
    let passwordConfirm = document.getElementById("passwordConfirm").value;
    let passwordConfirmFormatError = document.getElementById("passwordConfirmFormatError");
    if (password != passwordConfirm){
        passwordConfirmFormatError.innerText = "Mật khẩu không trùng khớp!";
        document.getElementById("submitButton").disabled = true;
    }
    else {
        passwordConfirmFormatError.innerText = "";
        document.getElementById("submitButton").disabled = false;
    }
}
function clearErrorPasswordConfirm (){
    let passwordConfirmFormatError = document.getElementById("passwordConfirmFormatError");
    passwordConfirmFormatError.innerText = "";
}
//B.ĐỔI MẬT KHẨU

function validatePhoneNumberUpdate(){
    let userName = document.getElementById("userName").value;
    let userNameForMatError = document.getElementById("userNameForMatError");
    if (!/^(84|0)([0-9]{7,12})$/.test(userName)){
        userNameForMatError.innerText = "Số điện thoại sai định dạng";
        document.getElementById("btn-update-pass").disabled = true;
    }
    else {
        userNameForMatError.innerText = "";
        document.getElementById("btn-update-pass").disabled = false;
    }
}
function clearErrorPhoneNumberUpdate (){
    let userNameForMatError = document.getElementById("userNameForMatError");
    userNameForMatError.innerText = "";
}


function validatePasswordUpdate(){
    let oldPass = document.getElementById("oldPass").value;
    let oldPasswordUpdateFormatError = document.getElementById("oldPasswordUpdateFormatError");
    if (!/^(?=.*[a-z])(?=.*\d)[a-z\d]{8,}$/.test(oldPass)){
        oldPasswordUpdateFormatError.innerText = "Mật khẩu ít nhất 8 kí tự. Bao gồm số, chữ(thường,không in hoa)";
        document.getElementById("btn-update-pass").disabled = true;
    }
    else {
        oldPasswordUpdateFormatError.innerText = "";
        document.getElementById("btn-update-pass").disabled = false;
    }
}
function clearErrorPasswordUpdate (){
    let oldPasswordUpdateFormatError = document.getElementById("oldPasswordUpdateFormatError");
    oldPasswordUpdateFormatError.innerText = "";
}

function validateNewPasswordUpdate(){
    let newPass = document.getElementById("newPass").value;
    let newPasswordFormatError = document.getElementById("newPasswordFormatError");
    if (!/^(?=.*[a-z])(?=.*\d)[a-z\d]{8,}$/.test(newPass)){
        newPasswordFormatError.innerText = "Mật khẩu ít nhất 8 kí tự. Bao gồm số, chữ(thường, không in hoa)";
        document.getElementById("btn-update-pass").disabled = true;
    }
    else {
        newPasswordFormatError.innerText = "";
        document.getElementById("btn-update-pass").disabled = false;
    }
}
function clearErrorNewPasswordUpdate (){
    let newPasswordUpdateFormatError = document.getElementById("newPasswordFormatError");
    newPasswordUpdateFormatError.innerText = "";
}

function validatePasswordUpdateConfirm(){
    let newPass = document.getElementById("newPass").value;
    let passConfirm = document.getElementById("passConfirm").value;
    let passwordConfirmError = document.getElementById("passwordConfirmError");
    if (newPass != passConfirm){
        passwordConfirmError.innerText = "Mật khẩu không trùng khớp!";
        document.getElementById("btn-update-pass").disabled = true;
    }
    else {
        passwordConfirmError.innerText = "";
        document.getElementById("btn-update-pass").disabled = false;
    }
}
function clearErrorPasswordUpdateConfirm (){
    let passwordConfirmError = document.getElementById("passwordConfirmError");
    passwordConfirmError.innerText = "";
}




