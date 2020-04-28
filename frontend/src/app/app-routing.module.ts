import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginPageComponent} from './login-page/login-page.component'
import {RegisterPageComponent} from './register-page/register-page.component'
//import { Full_ROUTES } from './layout/routes/main-layout.routes';
import { HomePageComponent } from './home-page/home-page.component';

const routes: Routes = [{
    path: 'login',
    component: LoginPageComponent
  },
  {
      path: 'home',
      component: HomePageComponent
    },
  { path: 'register', component: RegisterPageComponent, data: { title: 'Register' } }, //, children: Full_ROUTES },
  //{ path: '', component: ContentLayoutComponent, data: { title: 'content Views' }, children: CONTENT_ROUTES },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
