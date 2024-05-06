const passwordInput = document.querySelector(".password-input input");
const eyeIcon = document.querySelector(".eye-icon");
const reqIcon = document.querySelectorAll(".req");
const submitButton = document.getElementById('submit_button');
passwordInput.addEventListener("keyup", function()
{
  const password = this.value;
  if(password.length >= 8){
        reqIcon[0].classList.add('valid');
        reqIcon[0].classList.remove('invalid');
  }
  else{
        reqIcon[0].classList.remove('valid');
        reqIcon[0].classList.add('invalid');
  }

  if(/[a-z]/.test(password))
  {
          reqIcon[1].classList.add('valid');
          reqIcon[1].classList.remove('invalid');
  }
  else{
          reqIcon[1].classList.remove('valid');
          reqIcon[1].classList.add('invalid');
  }

  if(/[A-Z]/.test(password))
  {
          reqIcon[2].classList.add('valid');
          reqIcon[2].classList.remove('invalid');
  }
  else{
          reqIcon[2].classList.remove('valid');
          reqIcon[2].classList.add('invalid');
  }

  function hasSpecialCharacter(password)
  {
    const specialCharacters = ['!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '{', '}', '[', ']', ':', ';', '<', '>', '.', ',', '?', '/', '|', '\\', '\'', '\"', '`', '~'];
    for(let i = 0; i < password.length; i++)
    {
      if(specialCharacters.includes(password[i])) return true;
    }
    return false;
  }

  if(hasSpecialCharacter(password))
  {
          reqIcon[3].classList.add('valid');
          reqIcon[3].classList.remove('invalid');
  }
  else{
          reqIcon[3].classList.remove('valid');
          reqIcon[3].classList.add('invalid');
  }
  if(/[0-9]/.test(password))
  {
          reqIcon[4].classList.add('valid');
          reqIcon[4].classList.remove('invalid');
  }
  else{
          reqIcon[4].classList.remove('valid');
          reqIcon[4].classList.add('invalid');
  }
});

const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+{};:,<.>])[A-Za-z\d!@#$%^&*()\-_=+{};:,<.>.]{8,}$/;

function checkPassword(password) {
    return passwordRegex.test(password);
}

document.getElementById("regForm").addEventListener("submit", function(event) {
    event.preventDefault();
    if (checkPassword(passwordInput.value)){
    let formData = new FormData(document.getElementById("regForm"));
              formData.append("roles","USERROLE");
              for (const value of formData.values()) {
                          console.log(value);
              }
              fetch("/registration_js", {
                      method: "POST",
                      body: formData
              }).then(response => {
                  console.log("POST запрос выполнен успешно");
              }).catch(error => {
                   console.error('Ошибка при отправке запроса:', error);
              });
    }
    event.target.reset();
});



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
