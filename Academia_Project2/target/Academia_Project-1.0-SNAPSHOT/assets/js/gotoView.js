let view_btn = document.getElementById('view_bills');

view_btn.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
        location.href = "View_bills.html";
});