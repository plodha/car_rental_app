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

}
