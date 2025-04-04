document.addEventListener('DOMContentLoaded', function() {
    // Toggle collapse sections
    const collapsibles = document.querySelectorAll('.collapsible-section');
    
    collapsibles.forEach(function(item) {
        item.addEventListener('click', function() {
            const icon = this.querySelector('i');
            if (icon.classList.contains('bi-caret-down-fill')) {
                icon.classList.replace('bi-caret-down-fill', 'bi-caret-right-fill');
            } else {
                icon.classList.replace('bi-caret-right-fill', 'bi-caret-down-fill');
            }
        });
    });
});