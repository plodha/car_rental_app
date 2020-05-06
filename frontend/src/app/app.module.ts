import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { EmployeeHomePageComponent } from './employee-home-page/employee-home-page.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { VehiclePageComponent } from './vehicle-page/vehicle-page.component';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { HomePageComponent } from './home-page/home-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatRadioModule} from '@angular/material/radio';
import {MatDialogModule} from '@angular/material/dialog';
import {HttpClientModule} from '@angular/common/http';
import {MatMenuModule} from '@angular/material/menu';
import { AddVehiclePageComponent } from './add-vehicle-page/add-vehicle-page.component';
import { AddLocationPageComponent } from './add-location-page/add-location-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { AddReservationPageComponent } from './add-reservation-page/add-reservation-page.component';
import { DropoffSurveyPageComponent } from './dropoff-survey-page/dropoff-survey-page.component';
import { CancelPageComponent } from './cancel-page/cancel-page.component';

import { NgxMatDatetimePickerModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { NgxMatMomentModule } from '@angular-material-components/moment-adapter';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { CustomerLandingPageComponent } from './customer-landing-page/customer-landing-page.component';
import { ReservationsPageComponent } from './reservations-page/reservations-page.component';
import { LocationsPageComponent } from './locations-page/locations-page.component';
import { CustomersPageComponent } from './customers-page/customers-page.component';
import { PricePageComponent } from './price-page/price-page.component';
import { InvoicePageComponent } from './invoice-page/invoice-page.component';
import { EditVehiclePageComponent } from './edit-vehicle-page/edit-vehicle-page.component';
import { EditLocationPageComponent } from './edit-location-page/edit-location-page.component';
import { LogoutPageComponent } from './logout-page/logout-page.component';
import { VehicleTypePageComponent } from './vehicle-type-page/vehicle-type-page.component';
import { AddVehicletypePageComponent } from './add-vehicletype-page/add-vehicletype-page.component';
import { EditVehicletypePageComponent } from './edit-vehicletype-page/edit-vehicletype-page.component';
import { AddPricePageComponent } from './add-price-page/add-price-page.component';
import { EditPricePageComponent } from './edit-price-page/edit-price-page.component';
//import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    EmployeeHomePageComponent,
    AdminHomePageComponent,
    VehiclePageComponent,
    HomePageComponent,
    AddVehiclePageComponent,
    AddLocationPageComponent,
    ProfilePageComponent,
    AddReservationPageComponent,
    DropoffSurveyPageComponent,
    CancelPageComponent,
    CustomerLandingPageComponent,
    ReservationsPageComponent,
    LocationsPageComponent,
    CustomersPageComponent,
    PricePageComponent,
    InvoicePageComponent,
    EditVehiclePageComponent,
    EditLocationPageComponent,
    LogoutPageComponent,
    VehicleTypePageComponent,
    AddVehicletypePageComponent,
    EditVehicletypePageComponent,
    AddPricePageComponent,
    EditPricePageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    TooltipModule.forRoot(),
    BrowserAnimationsModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatButtonToggleModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,
    MatMenuModule,
    NgxMaterialTimepickerModule,
    MatRadioModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatMomentModule,
    MatDialogModule


  ],
  providers: [MatDatepickerModule,MatNativeDateModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
