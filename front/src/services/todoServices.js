import http from "../http-common";

class TodoDataService {
  getAll() {
    return http.get("/todos");
  }

  get(id) {
    return http.get(`/todos/${id}`);
  }

  create(data) {
    return http.post("/todos", data);
  }

  updateCompleted(id, data) {
    return http.patch(`/todos/${id}/completed`, data);
  }

  delete(id) {
    return http.delete(`/todos/${id}`);
  }
}

export default new TodoDataService();
