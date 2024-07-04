import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

const applicantService = {
  addApplicant({ firstName: firstName, lastName: lastName }) {
    return apiClient.post("/applicants", {
      firstName: firstName,
      lastName: lastName,
    });
  },

  getAllApplicants(name, status) {
    const params = {};
    if (name) params.name = name;
    if (status) params.status = status;

    return apiClient.get("/applicants", { params });
  },

  acceptApplicant(id) {
    return apiClient.patch(`/${id}/accept`);
  },

  declineApplicant(id) {
    return apiClient.patch(`/${id}/decline`);
  },
};

export default applicantService;
