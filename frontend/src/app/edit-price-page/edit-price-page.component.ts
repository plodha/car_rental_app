import { Component, OnInit } from '@angular/core';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

import {ActivatedRoute, Router } from '@angular/router';
import {ApiService} from '../api.service'

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
interface VehicleType {
  value: string;
  id: string;
}

@Component({
  selector: 'app-edit-price-page',
  templateUrl: './edit-price-page.component.html',
  styleUrls: ['./edit-price-page.component.scss']
})
export class EditPricePageComponent implements OnInit {

  vehicleTypes: VehicleType[] = [];
  editPriceForm:FormGroup;
  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();

  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,
      private api: ApiService) {
        this.actr.data.subscribe((data)=>{
          var vehicleTypes = data.vehicleType;
          console.log(vehicleTypes)
          var i = 0;
          for (i = 0; i < vehicleTypes.length; i++) {

            var obj = vehicleTypes[i];

            var vehicleType = {
              id: obj.id,
              value: obj.vehicleClass
            }
            this.vehicleTypes[i] = vehicleType;
          }

        });

       }

  ngOnInit(): void {

    var priceId = this.actr.snapshot.params.id
    this.editPriceForm = this.formBuilder.group({
      id:[null,Validators.required],
      vehicleType:[null,Validators.required],
      lateFee:[null,[Validators.required, Validators.pattern("^[0-9]+(\\.[0-9]{2})?$"),Validators.maxLength(5)]],
      hourlyPrice:[null,[Validators.required, Validators.pattern("^[0-9]+(\\.[0-9]{2})?$"),Validators.maxLength(5)]],
      hourlyRange:[null,[Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(5)]]
    });

    this.actr.data.subscribe((data)=>{
      var prices = data.price;
      var i = 0;
      for (i = 0; i < prices.length; i++) {
        var obj = prices[i];
        if(obj.id == priceId){
          this.editPriceForm.setValue({
            id : obj.id,
            hourlyPrice : obj.hourlyPrice,
            hourlyRange : obj.hourlyRange,
            lateFee: obj.lateFee,
            vehicleType: obj.vehicleTypeId.id
          });
        }
      }
      });


  }

  onFormSubmit() {
    this.isLoadingResults = true;
    //this.router.navigate(['/login']);

    var formData = {}
    formData['id'] = this.editPriceForm.get('id').value
    formData['hourlyRange'] = this.editPriceForm.get('hourlyRange').value
    formData['hourlyPrice'] = parseFloat(this.editPriceForm.get('hourlyPrice').value)
    formData['lateFee'] = parseFloat(this.editPriceForm.get('lateFee').value)
    var vehicleType = {}
    vehicleType['id'] = this.editPriceForm.get('vehicleType').value
    formData['vehicleTypeId'] = vehicleType


    this.api.updatePriceAPI(formData).subscribe((res:any)=>{
      this.router.navigate(['/priceList']);
    });

  }
getPriceById(id:any){
  var object = this;
 this.api.getLocationById(id).subscribe((data: any) => {

   console.log(data)
   this.editPriceForm.setValue({
     id : data.id,
     hourlyPrice : data.hourlyPrice,
     hourlyRange : data.hourlyRange,
     lateFee: data.lateFee,
     vehicleType: data.vehicleTypeId.id
   });
 });
}

}
