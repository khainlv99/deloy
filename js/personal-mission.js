document.addEventListener('DOMContentLoaded', function() {
    // Chức năng toggle cho danh mục nhiệm vụ
    document.querySelectorAll('.task-category').forEach(item => {
        item.addEventListener('click', event => {
            const icon = item.querySelector('i');
            if (icon.classList.contains('bi-caret-down-fill')) {
                icon.classList.remove('bi-caret-down-fill');
                icon.classList.add('bi-caret-right-fill');
                
                // Ẩn các nhiệm vụ trong danh mục
                let nextElement = item.parentElement.querySelector('.task-row');
                while (nextElement) {
                    nextElement.style.display = 'none';
                    nextElement = nextElement.nextElementSibling;
                    if (nextElement && nextElement.classList.contains('task-category')) break;
                }
            } else {
                icon.classList.remove('bi-caret-right-fill');
                icon.classList.add('bi-caret-down-fill');
                
                // Hiển thị các nhiệm vụ trong danh mục
                let nextElement = item.parentElement.querySelector('.task-row');
                while (nextElement) {
                    nextElement.style.display = 'block';
                    nextElement = nextElement.nextElementSibling;
                    if (nextElement && nextElement.classList.contains('task-category')) break;
                }
            }
        });
    });
    
    // Mở modal khi click vào trạng thái
    document.querySelectorAll('.status-badge').forEach(item => {
        item.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('updateStatusModal'));
            modal.show();
        });
    });
});