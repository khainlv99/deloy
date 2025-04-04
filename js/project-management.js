// Kiểm tra xác thực và hiển thị thông tin người dùng khi trang tải
document.addEventListener('DOMContentLoaded', function() {
    // Kiểm tra đã đăng nhập chưa
    const currentUserJSON = localStorage.getItem('currentUser');
    if (!currentUserJSON) {
        // Nếu chưa đăng nhập, chuyển về trang đăng nhập
        window.location.href = 'login.html';
        return;
    }
    
    // Hiển thị tên người dùng
    const currentUser = JSON.parse(currentUserJSON);
    
    // Thêm thông tin người dùng vào header
    const userInfoContainer = document.querySelector('.header .container .d-flex');
    if (userInfoContainer) {
        // Kiểm tra xem phần tử hiển thị tên người dùng đã tồn tại chưa
        let userSpan = userInfoContainer.querySelector('.user-info');
        if (!userSpan) {
            // Tạo phần tử hiển thị tên người dùng
            userSpan = document.createElement('div');
            userSpan.className = 'user-info me-3';
            userSpan.innerHTML = `<i class="bi bi-person-circle"></i> ${currentUser.fullName}`;
            userInfoContainer.prepend(userSpan);
        }
    }
    
    // Thiết lập sự kiện đăng xuất
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.style.cursor = 'pointer';
        logoutBtn.addEventListener('click', function() {
            localStorage.removeItem('currentUser');
            window.location.href = 'login.html';
        });
    }
    
    // Tải danh sách dự án
    loadProjects();
});

// Xử lý hiển thị dự án từ localStorage
function loadProjects() {
    const projects = JSON.parse(localStorage.getItem('projects')) || [];
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    
    // Lọc dự án của người dùng hiện tại
    const userProjects = projects.filter(project => project.userId === currentUser.id);
    
    const tableBody = document.querySelector('.task-table tbody');
    if (!tableBody) {
        console.error('Không tìm thấy phần tử tbody trong bảng.');
        return;
    }
    
    tableBody.innerHTML = ''; // Xóa dữ liệu mẫu
    
    if (userProjects.length === 0) {
        // Hiển thị thông báo nếu không có dự án
        const emptyRow = document.createElement('tr');
        emptyRow.innerHTML = `
            <td colspan="3" class="text-center">Bạn chưa có dự án nào. Hãy thêm dự án mới!</td>
        `;
        tableBody.appendChild(emptyRow);
    } else {
        // Hiển thị danh sách dự án
        userProjects.forEach((project, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${index + 1}</td>
                <td>${project.name}</td>
                <td class="text-end">
                    <button class="btn btn-action btn-yellow" data-id="${project.id}">Sửa</button>
                    <button class="btn btn-action btn-red" data-id="${project.id}">Xóa</button>
                    <button class="btn btn-action btn-blue" data-id="${project.id}">Chi tiết</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
        
        // Thêm sự kiện cho các nút sau khi thêm vào DOM
        addButtonEventListeners();
    }
}

// Thêm sự kiện cho các nút
function addButtonEventListeners() {
    // Nút sửa
    document.querySelectorAll('.btn-yellow').forEach(button => {
        button.addEventListener('click', function() {
            const projectId = parseInt(this.getAttribute('data-id'));
            editProject(projectId);
        });
    });
    
    // Nút xóa
    document.querySelectorAll('.btn-red').forEach(button => {
        button.addEventListener('click', function() {
            const projectId = parseInt(this.getAttribute('data-id'));
            if (confirm('Bạn có chắc chắn muốn xóa dự án này?')) {
                deleteProject(projectId);
            }
        });
    });
    
    // Nút chi tiết
    document.querySelectorAll('.btn-blue').forEach(button => {
        button.addEventListener('click', function() {
            const projectId = parseInt(this.getAttribute('data-id'));
            viewProjectDetails(projectId);
        });
    });
}

// Xử lý chỉnh sửa dự án
function editProject(projectId) {
    const projects = JSON.parse(localStorage.getItem('projects')) || [];
    const project = projects.find(p => p.id === projectId);
    
    if (project) {
        const projectNameInput = document.querySelector('#addProjectModal input[type="text"]');
        const projectDescriptionInput = document.querySelector('#addProjectModal textarea');
        const saveButton = document.querySelector('#addProjectModal .btn-primary');
        const modalTitle = document.querySelector('#addProjectModal .modal-title');
        
        if (!projectNameInput || !projectDescriptionInput || !saveButton || !modalTitle) {
            console.error('Không tìm thấy phần tử trong modal.');
            return;
        }
        
        // Điền thông tin dự án vào modal
        projectNameInput.value = project.name;
        projectDescriptionInput.value = project.description || '';
        
        // Đặt ID dự án đang chỉnh sửa
        saveButton.setAttribute('data-editing-id', project.id);
        
        // Cập nhật tiêu đề modal
        modalTitle.textContent = 'Chỉnh sửa dự án';
        
        // Hiển thị modal
        const modal = new bootstrap.Modal(document.getElementById('addProjectModal'));
        modal.show();
    }
}

// Xử lý xóa dự án
function deleteProject(projectId) {
    let projects = JSON.parse(localStorage.getItem('projects')) || [];
    projects = projects.filter(p => p.id !== projectId);
    localStorage.setItem('projects', JSON.stringify(projects));
    
    // Tải lại danh sách dự án
    loadProjects();
}

// Xử lý xem chi tiết dự án
function viewProjectDetails(projectId) {
    // Chức năng này có thể được phát triển sau
    alert('Chức năng xem chi tiết dự án sẽ được phát triển sau');
}

// Xử lý thêm dự án mới sau khi DOM đã tải
document.addEventListener('DOMContentLoaded', function() {
    const addProjectBtn = document.querySelector('#addProjectModal .btn-primary');
    
    if (addProjectBtn) {
        addProjectBtn.addEventListener('click', function() {
            const projectNameInput = document.querySelector('#addProjectModal input[type="text"]');
            const projectDescriptionInput = document.querySelector('#addProjectModal textarea');
            
            if (!projectNameInput || !projectDescriptionInput) {
                console.error('Không tìm thấy phần tử input trong modal.');
                return;
            }
            
            // Kiểm tra tên dự án không được để trống
            if (!projectNameInput.value.trim()) {
                alert('Tên dự án không được để trống');
                return;
            }
            
            // Lấy ID dự án đang chỉnh sửa (nếu có)
            const editingProjectId = this.getAttribute('data-editing-id');
            const currentUser = JSON.parse(localStorage.getItem('currentUser'));
            
            if (editingProjectId) {
                // Cập nhật dự án hiện có
                let projects = JSON.parse(localStorage.getItem('projects')) || [];
                const projectIndex = projects.findIndex(p => p.id === parseInt(editingProjectId));
                
                if (projectIndex !== -1) {
                    projects[projectIndex].name = projectNameInput.value.trim();
                    projects[projectIndex].description = projectDescriptionInput.value.trim();
                    localStorage.setItem('projects', JSON.stringify(projects));
                }
                
                // Xóa thuộc tính data-editing-id
                this.removeAttribute('data-editing-id');
            } else {
                // Tạo dự án mới
                const newProject = {
                    id: Date.now(),
                    name: projectNameInput.value.trim(),
                    description: projectDescriptionInput.value.trim(),
                    userId: currentUser.id
                };
                
                // Lưu dự án vào localStorage
                let projects = JSON.parse(localStorage.getItem('projects')) || [];
                projects.push(newProject);
                localStorage.setItem('projects', JSON.stringify(projects));
            }
            
            // Đóng modal
            const modal = bootstrap.Modal.getInstance(document.getElementById('addProjectModal'));
            if (modal) {
                modal.hide();
            }
            
            // Làm sạch form
            projectNameInput.value = '';
            projectDescriptionInput.value = '';
            
            // Tải lại danh sách dự án
            loadProjects();
        });
    }
    
    // Xử lý tìm kiếm dự án
    const searchInput = document.querySelector('input[placeholder="Tìm kiếm dự án..."]');
    if (searchInput) {
        searchInput.addEventListener('input', function() {
            const searchText = this.value.toLowerCase();
            const rows = document.querySelectorAll('.task-table tbody tr');
            
            rows.forEach(row => {
                const projectNameCell = row.querySelector('td:nth-child(2)');
                if (projectNameCell) {
                    const projectName = projectNameCell.textContent.toLowerCase();
                    row.style.display = projectName.includes(searchText) ? '' : 'none';
                }
            });
        });
    }
});