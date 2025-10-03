document.addEventListener("DOMContentLoaded", () => {
    // Limpar dados do JWT do localStorage
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('userData');
    
    console.log("🚪 Logout realizado - Tokens JWT removidos");
    
    setTimeout(() => {
        window.location.href = "Login.html";
    }, 1000);
});