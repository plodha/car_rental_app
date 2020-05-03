import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginPageComponent} from './login-page/login-page.component'
import {RegisterPageComponent} from './register-page/register-page.component'
//import { Full_ROUTES } from './layout/routes/main-layout.routes';
import { HomePageComponent } from './home-page/home-page.component';
import {AdminHomePageComponent} from './admin-home-page/admin-home-page.component'
import {VehiclePageComponent} from './vehicle-page/vehicle-page.component'

import {AddVehiclePageComponent} from './add-vehicle-page/add-vehicle-page.component'
import {AddLocationPageComponent} from './add-location-page/add-location-page.component'
import {AddReservationPageComponent} from './add-reservation-page/add-reservation-page.component'

import {ProfilePageComponent} from './profile-page/profile-page.component'
import {DropoffSurveyPageComponent } from './dropoff-survey-page/dropoff-survey-page.component'
import {CancelPageComponent} from './cancel-page/cancel-page.component'
import { CustomerLandingPageComponent } from './customer-landing-page/customer-landing-page.component';
import { ReservationsPageComponent } from './reservations-page/reservations-page.component';
import { LocationsPageComponent } from './locations-page/locations-page.component';
import { CustomersPageComponent } from './customers-page/customers-page.component';
import { PricePageComponent } from './price-page/price-page.component';
import { InvoicePageComponent } from './invoice-page/invoice-page.component';

const routes: Routes = [{
    path: 'login',
    component: LoginPageComponent
  },
  {
      path: 'home',
      component: HomePageComponent
    },
    {
        path: 'admin',
        component: AdminHomePageComponent
      },
      {
          path:'vehicle',
          component: VehiclePageComponent
      },
      {
          path:'addVehicle',
          component: AddVehiclePageComponent
      },
      {
          path:'addLocation',
          component: AddLocationPageComponent
      },
      {
          path:'addReservation',
          component: AddReservationPageComponent
      },
      {
          path:'profile',
          component: ProfilePageComponent
      },
      {
          path:'survey',
          component: DropoffSurveyPageComponent
      },
      {
          path:'cancel',
          component: CancelPageComponent
      },
      {
          path:'customerPage',
          component: CustomerLandingPageComponent
      },
      {
          path:'locations',
          component: LocationsPageComponent
      },
      {
          path:'reservations',
          component: ReservationsPageComponent
      },
      {
          path:'customers',
          component: CustomersPageComponent
      },
      {
          path:'priceList',
          component: PricePageComponent
      },
      {
          path:'invoice',
          component: InvoicePageComponent
      },
  { path: 'register', component: RegisterPageComponent, data: { title: 'Register' } }, //, children: Full_ROUTES },
  //{ path: '', component: ContentLayoutComponent, data: { title: 'content Views' }, children: CONTENT_ROUTES },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
