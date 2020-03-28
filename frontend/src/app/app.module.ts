import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { EmployeeHomePageComponent } from './employee-home-page/employee-home-page.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { VehiclePageComponent } from './vehicle-page/vehicle-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    EmployeeHomePageComponent,
    AdminHomePageComponent,
    VehiclePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
