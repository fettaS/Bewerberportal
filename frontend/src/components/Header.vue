<template>
    <!-- Disclosure component as navigation -->
    <Disclosure as="nav" class="bg-white">
      <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
        <!-- Top bar layout -->
        <div class="relative flex h-16 items-center justify-between">
          <!-- Left section: Logo -->
          <div class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
            <div class="flex flex-shrink-0 items-center">
              <!-- Logo image -->
              <img class="h-8 w-auto" src="../assets/immocloud-b-retina.webp" alt="Immocloud" />
            </div>
          </div>
          <!-- Right section: Title -->
          <div>
            <p class="text-xl">Bewerberportal</p>
          </div>
        </div>
      </div>
    </Disclosure>
  </template>
  
  <script setup>
  import { Disclosure } from '@headlessui/vue';
  
  // No need for additional script logic in this case
  </script>
  