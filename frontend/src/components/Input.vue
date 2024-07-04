<template>
    <div>
      <!-- Form for adding new applicant -->
      <form @submit.prevent="submitForm">
        <div class="grid grid-cols-3 items-end gap-10 p-32">
          <!-- First name input -->
          <div>
            <label for="first_name" class="block mb-2 text-sm font-medium text-gray-900">Vorname*</label>
            <input 
              type="text" 
              id="first_name" 
              v-model="formData.firstName"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" 
              placeholder="John" 
              required 
            />
          </div>
          <!-- Last name input -->
          <div>
            <label for="last_name" class="block mb-2 text-sm font-medium text-gray-900">Nachname*</label>
            <input 
              type="text" 
              id="last_name" 
              v-model="formData.lastName"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" 
              placeholder="Doe" 
              required 
            />
          </div>
          <!-- Submit button -->
          <div>
            <button 
              type="submit" 
              class="text-white bg-[#13aba9] hover:bg-[#13aba9] focus:ring-4 focus:ring-[#13aba9] font-medium rounded-lg text-sm px-5 py-2.5"
            >
              Submit
            </button>
          </div>
        </div>
      </form>
  
      <!-- Display dashboard with applicant list -->
      <div>
        <Dashboard :applicants="applicantList" @updateApplicants="getApplicants"/>
      </div>
    </div>
  </template>
  
  <script setup>
  import { onMounted, ref, reactive } from 'vue';
  import applicantService from '../services/ApplicantService';
  import Dashboard from '@/components/Dashboard.vue';
  
  // Reactive form data for two-way binding
  const formData = reactive({
    firstName: '',
    lastName: ''
  });
  
  // Reference for the list of applicants
  const applicantList = ref([]);
  
  // Function to handle form submission
  const submitForm = async () => {
    try {
      // Call service to add new applicant
      await applicantService.addApplicant({
        firstName: formData.firstName,
        lastName: formData.lastName
      });
      console.log('Form submitted successfully!');
      getApplicants(); // Refresh the list after submission
    } catch (error) {
      console.error('Error adding user:', error);
    }
  };
  
  // Function to fetch all applicants from the service
  const getApplicants = () => {
    applicantService.getAllApplicants().then(response => {
      applicantList.value = response.data;
    }).catch(error => {
      console.error('Error loading applicants:', error);
    });
  };
  
  // Fetch applicants on component mount
  onMounted(() => {
    getApplicants();
  });
  </script>
  