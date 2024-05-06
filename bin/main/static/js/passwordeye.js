const eyeIcon = document.querySelector(".eye-icon");
const passwordInput = document.querySelector(".password-input input");
eyeIcon.addEventListener("click", () =>
{
  if(passwordInput.type === "password")
  {
    passwordInput.type = "text";
    eyeIcon.src = "images/eye-slash-solid.svg"
  }
  else
  {
    passwordInput.type = "password";
    eyeIcon.src = "images/eye-solid.svg";
  }
});