export const setToken = user => {
  localStorage.setItem("id", user.id);
};

export const getToken = () => {
  return localStorage.getItem("id");
};
export const removeToken = () => {
  return localStorage.removeItem("id");
};
