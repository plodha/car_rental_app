import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginPageComponent} from './login-page/login-page.component'
import {RegisterPageComponent} from './register-page/register-page.component'
//import { Full_ROUTES } from './layout/routes/main-layout.routes';
import { HomePageComponent } from './home-page/home-page.component';
import {AdminHomePageComponent} from './admin-home-page/admin-home-page.component'
import {VehiclePageComponent} from './vehicle-page/vehicle-page.component'

import {AddVehiclePageComponent} from './add-vehicle-page/add-vehicle-page.component'
import {EditVehiclePageComponent} from './edit-vehicle-page/edit-vehicle-page.component'

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
import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

import { Resolve, ActivatedRouteSnapshot } from '@angular/router';


@Injectable()
export class LocationResolver implements Resolve<any> {
  constructor(private apiService: ApiService) {}

  resolve() {
    return this.apiService.getAllLocations();
  }
}

@Injectable()
export class CustomerResolver implements Resolve<any> {
  constructor(private apiService: ApiService) {}

  resolve() {
    return this.apiService.getAllCustomers();
  }
}
@Injectable()
export class VehicleResolver implements Resolve<any> {
  constructor(private apiService: ApiService) {}

  resolve() {
    return this.apiService.getAllVehicles();
  }
}

@Injectable()
export class VehicleTypeResolver implements Resolve<any> {
  constructor(private apiService: ApiService) {}

  resolve() {
    return this.apiService.getAllVehicleTypes();
  }
}
@Injectable()
export class DamageResolver implements Resolve<any> {
  constructor(private apiService: ApiService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.apiService.getAllDamageType(route.params.id);
  }
}
@Injectable()
export class ReservationResolver implements Resolve<any> {
  constructor(private apiService: ApiService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.apiService.getAllReservationsForUser(route.params.id);
  }
}
/*This hsould be updated for all prices*/
@Injectable()
export class PriceResolver implements Resolve<any> {
  constructor(private apiService: ApiService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.apiService.getPriceInfo(route.params.id);
  }
}

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
          component: VehiclePageComponent,
          resolve: {
            vehicle: VehicleResolver
          }
      },
      {
          path:'addVehicle',
          component: AddVehiclePageComponent,
          resolve: {
            location: LocationResolver,
            vehicleType: VehicleTypeResolver
          }
      },
      {
          path:'editVehicle/:id',
          component: EditVehiclePageComponent,
          resolve: {
            location: LocationResolver,
            vehicleType: VehicleTypeResolver
          }
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
          component: LocationsPageComponent,
          resolve: {
           location: LocationResolver
       }
      },
      {
          path:'reservations/:id',
          component: ReservationsPageComponent,
          resolve: {
            reservation : ReservationResolver
          }
      },
      {
          path:'customers',
          component: CustomersPageComponent,
          resolve: {
            customer:CustomerResolver
          }
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
  exports: [RouterModule],
  providers: [LocationResolver,CustomerResolver, ReservationResolver,PriceResolver,VehicleTypeResolver,VehicleResolver, DamageResolver]
})
export class AppRoutingModule { }
