function toggleForms() {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    
    loginForm.classList.toggle('hide');
    registerForm.classList.toggle('hide');
}
function toggleForms() {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    
    loginForm.classList.toggle('hide');
    registerForm.classList.toggle('hide');
    // Kiểm tra đã đăng nhập hay chưa
document.addEventListener('DOMContentLoaded', function() {
    // Kiểm tra nếu đã đăng nhập thì chuyển đến trang quản lý dự án
    if (localStorage.getItem('currentUser')) {
        window.location.href = 'project-management.html';
    }
});

// Chuyển đổi giữa form đăng nhập và đăng ký
function toggleForms() {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    
    loginForm.classList.toggle('hide');
    registerForm.classList.toggle('hide');
}

// Hiển thị thông báo lỗi
function showError(inputElement, message) {
    // Xóa thông báo lỗi cũ nếu có
    const existingError = inputElement.parentElement.querySelector('.error-message');
    if (existingError) {
        existingError.remove();
    }
    
    // Tạo và hiển thị thông báo lỗi mới
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;
    errorDiv.style.color = 'red';
    errorDiv.style.fontSize = '14px';
    errorDiv.style.marginTop = '5px';
    
    // Thêm viền đỏ cho input
    inputElement.style.borderColor = 'red';
    
    // Thêm thông báo lỗi vào DOM
    inputElement.parentElement.appendChild(errorDiv);
}

// Xóa thông báo lỗi
function clearError(inputElement) {
    const existingError = inputElement.parentElement.querySelector('.error-message');
    if (existingError) {
        existingError.remove();
    }
    inputElement.style.borderColor = '';
}

// Kiểm tra email hợp lệ
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Xử lý đăng ký
document.querySelector('#registerForm form').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const nameInput = document.getElementById('registerName');
    const emailInput = document.getElementById('registerEmail');
    const passwordInput = document.getElementById('registerPassword');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    
    let isValid = true;
    
    // Xóa tất cả thông báo lỗi cũ
    clearError(nameInput);
    clearError(emailInput);
    clearError(passwordInput);
    clearError(confirmPasswordInput);
    
    // Kiểm tra họ và tên
    if (!nameInput.value.trim()) {
        showError(nameInput, 'Họ và tên không được để trống');
        isValid = false;
    }
    
    // Kiểm tra email
    if (!emailInput.value.trim()) {
        showError(emailInput, 'Email không được để trống');
        isValid = false;
    } else if (!isValidEmail(emailInput.value.trim())) {
        showError(emailInput, 'Email không đúng định dạng');
        isValid = false;
    }
    
    // Kiểm tra mật khẩu
    if (!passwordInput.value) {
        showError(passwordInput, 'Mật khẩu không được để trống');
        isValid = false;
    } else if (passwordInput.value.length < 8) {
        showError(passwordInput, 'Mật khẩu phải có tối thiểu 8 ký tự');
        isValid = false;
    }
    
    // Kiểm tra mật khẩu xác nhận
    if (!confirmPasswordInput.value) {
        showError(confirmPasswordInput, 'Xác nhận mật khẩu không được để trống');
        isValid = false;
    } else if (confirmPasswordInput.value !== passwordInput.value) {
        showError(confirmPasswordInput, 'Mật khẩu xác nhận không trùng khớp');
        isValid = false;
    }
    
    // Nếu tất cả đều hợp lệ, tiến hành đăng ký
    if (isValid) {
        // Tạo người dùng mới
        const newUser = {
            id: Date.now(), // Tạo ID ngẫu nhiên
            fullName: nameInput.value.trim(),
            email: emailInput.value.trim(),
            password: passwordInput.value
        };
        
        // Kiểm tra xem email đã tồn tại chưa
        let users = JSON.parse(localStorage.getItem('users')) || [];
        const existingUser = users.find(user => user.email === newUser.email);
        
        if (existingUser) {
            showError(emailInput, 'Email này đã được sử dụng');
            return;
        }
        
        // Lưu người dùng vào localStorage
        users.push(newUser);
        localStorage.setItem('users', JSON.stringify(users));
        
        // Đăng nhập người dùng mới
        localStorage.setItem('currentUser', JSON.stringify(newUser));
        
        // Chuyển hướng đến trang quản lý dự án
        window.location.href = 'project-management.html';
    }
});

// Xử lý đăng nhập
document.querySelector('#loginForm form').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const emailInput = document.getElementById('loginEmail');
    const passwordInput = document.getElementById('loginPassword');
    
    let isValid = true;
    
    // Xóa tất cả thông báo lỗi cũ
    clearError(emailInput);
    clearError(passwordInput);
    
    // Kiểm tra email
    if (!emailInput.value.trim()) {
        showError(emailInput, 'Email không được để trống');
        isValid = false;
    }
    
    // Kiểm tra mật khẩu
    if (!passwordInput.value) {
        showError(passwordInput, 'Mật khẩu không được để trống');
        isValid = false;
    }
    
    // Nếu form hợp lệ, kiểm tra thông tin đăng nhập
    if (isValid) {
        // Lấy danh sách người dùng từ localStorage
        const users = JSON.parse(localStorage.getItem('users')) || [];
        
        // Tạo mẫu người dùng test nếu chưa có dữ liệu
        if (users.length === 0) {
            users.push({
                id: 1,
                fullName: "An Nguyễn",
                email: "nguyenquangan@gmail.com",
                password: "123456"
            });
            localStorage.setItem('users', JSON.stringify(users));
        }
        
        // Tìm người dùng có email trùng khớp
        const user = users.find(user => user.email === emailInput.value.trim());
        
        if (!user) {
            showError(emailInput, 'Email không tồn tại');
            return;
        }
        
        // Kiểm tra mật khẩu
        if (user.password !== passwordInput.value) {
            showError(passwordInput, 'Mật khẩu không đúng');
            return;
        }
        
        // Đăng nhập thành công
        localStorage.setItem('currentUser', JSON.stringify(user));
        
        // Chuyển hướng đến trang quản lý dự án
        window.location.href = 'project-management.html';
    }
});
}