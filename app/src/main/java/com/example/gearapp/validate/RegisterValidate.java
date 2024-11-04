package com.example.gearapp.validate;

public class RegisterValidate {

    // Kiểm tra username không được rỗng và độ dài tối thiểu
    public static boolean validateUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.length() >= 4;
    }

    // Kiểm tra email hợp lệ bằng regex
    public static boolean validateEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email != null && email.matches(emailPattern);
    }

    // Kiểm tra password mạnh, bao gồm ít nhất 8 ký tự, có ký tự số, chữ thường, chữ hoa, và ký tự đặc biệt
    public static boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }

    public static boolean validatePhone(String phone) {
        // Số điện thoại hợp lệ với độ dài từ 10 đến 15 chữ số và cho phép dấu "+" ở đầu
        String phonePattern = "^[+]?[0-9]{10,15}$";
        return phone != null && phone.matches(phonePattern);
    }

    // Kiểm tra confirm password có khớp với password không
    public static boolean validateConfirmPassword(String password, String repassword) {
        return password != null && password.equals(repassword);
    }


}
