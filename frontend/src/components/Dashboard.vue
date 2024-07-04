<template>
    <div class="flex flex-col bg-white px-32">
  
      <!-- Search input -->
      <div class="justify-between">
        <div class="relative m-[2px] mb-3 mr-5 float-left">
          <label for="inputSearch" class="sr-only">Search </label>
          <input id="inputSearch" type="text" v-model="searchQuery" placeholder="Suche..." class="block w-64 rounded-lg border border-black py-2 pl-10 pr-4 text-sm focus:border-blue-400 focus:outline-none focus:ring-1 focus:ring-blue-400" />
          <span class="pointer-events-none absolute left-3 top-1/2 -translate-y-1/2 transform">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-4 w-4 text-neutral-500 dark:text-neutral-200">
              <path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z" />
            </svg>
          </span>
        </div>
  
        <!-- Filter -->
        <div class="relative m-[2px] mb-3 float-right hidden sm:block">
          <div class="flex items-center ps-4">
            <input id="bordered-checkbox-1" type="checkbox" v-model="hideDeclined" class="w-4 h-4 text-blue-600 focus:ring-blue-500 ">
            <label for="bordered-checkbox-1" class="w-full py-4 ms-2 text-sm font-medium text-gray-900">Dont show declined applicants</label>
          </div>
        </div>
      </div>
  
      <!-- Table -->
      <table class="min-w-full text-left text-sm whitespace-nowrap">
  
        <!-- Table head -->
        <thead class="uppercase tracking-wider border-b-2 dark:border-neutral-600">
          <tr>
            <th scope="col" class="px-6 py-4">
              Vorname
            </th>
            <th scope="col" class="px-6 py-4">
              Nachname
            </th>
            <th scope="col" class="px-6 py-4">
              Status
            </th>
            <th scope="col" class="px-6 py-4">
              Aktion
            </th>
          </tr>
        </thead>
  
        <!-- Table body -->
        <tbody>
          <tr v-for="applicant in filteredApplicants" :key="applicant.id" class="border-b dark:border-neutral-600">
            <th scope="row" class="px-6 py-4">
              {{ applicant.firstName }}
            </th>
            <td class="px-6 py-4">{{ applicant.lastName }}</td>
            <td class="px-6 py-4" 
                :class="{
                  'bg-[#13aba9]': applicant.status === 'ACCEPTED',
                  'bg-red-500': applicant.status === 'DECLINED',
                  'bg-white': applicant.status === 'OPEN'
                }">
              {{ applicant.status }}
            </td>
            <td class="px-6 py-4">
              <button v-if="applicant.status === 'OPEN'"
                      type="button" 
                      class="text-bg-[#13aba9] border border-emerald-500 hover:bg-emerald-500 hover:text-white focus:ring-4 focus:outline-none focus:ring-emerald-500 font-medium rounded-lg text-sm p-2.5 text-center inline-flex items-center me-2"
                      @click="updateApplicantStatus(applicant.id, 'ACCEPTED')">
                <img class="w-6 h-6" src = "../assets/thumbs-up-svgrepo-com.svg" alt="akzeptieren"/>
                <span class="sr-only">akzeptieren</span>
              </button>
              <button v-if="applicant.status === 'OPEN'" 
                      type="button" 
                      class="text-red-500 border border-red-500 hover:bg-red-500 hover:text-white focus:ring-4 focus:outline-none focus:ring-red-500 font-medium rounded-lg text-sm p-2.5 text-center inline-flex items-center me-2"
                      @click="updateApplicantStatus(applicant.id, 'DECLINED')">
                <img class="w-6 h-6" src = "../assets/x-symbol-svgrepo-com.svg" alt="akzeptieren"/>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, watchEffect, watch } from 'vue';
  import applicantService from '../services/ApplicantService';
  
  const props = defineProps(['applicants']);
  const emit = defineEmits(['updateApplicants']);
  
  const applicants = ref([...props.applicants]);
  const hideDeclined = ref(false);
  const searchQuery = ref('');
  
  watchEffect(() => {
    applicants.value = [...props.applicants];
  });
  
  const filteredApplicants = computed(() => {
    let filtered = applicants.value;
  
    if (hideDeclined.value) {
      filtered = filtered.filter(applicant => applicant.status !== 'DECLINED');
    }
  
    if (searchQuery.value.length >= 3) {
      const query = searchQuery.value.toLowerCase();
      filtered = filtered.filter(applicant => 
        applicant.firstName.toLowerCase().includes(query) || 
        applicant.lastName.toLowerCase().includes(query)
      );
    }
  
    return filtered;
  });
  
  async function updateApplicantStatus(applicantId, newStatus) {
    try {
      if (newStatus === 'ACCEPTED') {
        await applicantService.acceptApplicant(applicantId);
      } else {
        await applicantService.declineApplicant(applicantId);
      }
  
      // Emit event to update parent component
      emit('updateApplicants');
    } catch (error) {
      console.error('Error updating applicant status:', error);
    }
  }
  
  watch(searchQuery, (newQuery) => {
    if (newQuery.length >= 3) {
      filteredApplicants.value = applicants.value.filter(applicant => 
        applicant.firstName.toLowerCase().includes(newQuery.toLowerCase()) ||
        applicant.lastName.toLowerCase().includes(newQuery.toLowerCase())
      );
    }
  });
  </script>
  