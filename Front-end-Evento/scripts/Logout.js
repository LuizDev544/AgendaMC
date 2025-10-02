document.addEventListener("DOMContentLoaded", () => {
    // Limpar dados do JWT do localStorage
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('userData');
    
    console.log("🚪 Logout realizado - Tokens JWT removidos");
    
    // 🔧 REMOVER: Chamada para endpoint de logout que não existe
    // fetch("http://localhost:8080/auth/logout", {
    //     method: "POST",
    //     headers: {
    //         "Content-Type": "application/json"
    //     }
    // }).catch(err => console.log("Erro no logout backend:", err));
    
    // Redirecionar para login
    setTimeout(() => {
        window.location.href = "Login.html";
    }, 1000);
});