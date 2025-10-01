function logout() {
  localStorage.removeItem("authHeader");
  localStorage.removeItem("isAdmin");
  window.location.href = "Login.html";
}