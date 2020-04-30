import { Component, OnInit, SimpleChanges } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {ApiService} from '../api.service'

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  registrationValid

  constructor(public api: ApiService) { }

  ngOnInit(): void {
  }

  ngOnChange(changes: SimpleChanges) : void {
    console.log("here");
    console.log(changes);
  }

  update() {
    console.log(this.registrationValid.status)
  }

  registration() {
    console.log("Start");
    var test = this.api.registrationAPI("wqureshi1", "notsecure", "Wasae", "Qureshi", "747324", "05/23/1994", "43454", "random street", "random city", "ca", "94086").subscribe(
      res => {
        this.registrationValid = res;
        this.update()
      }
    );
  }
}
