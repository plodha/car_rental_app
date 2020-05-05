import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-customer-landing-page',
  templateUrl: './customer-landing-page.component.html',
  styleUrls: ['./customer-landing-page.component.scss']
})
export class CustomerLandingPageComponent implements OnInit {

  constructor(private router:Router) {

   }

  ngOnInit(): void {
    console.log('username');
    console.log(localStorage.username != undefined)
    if(localStorage.username != undefined ) {
      document.getElementById('profileName').innerHTML += localStorage.username
    }
    else {
      this.router.navigate(['/login']);

    }




  }

}
