import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import {ApiService} from '../api.service'

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-edit-location-page',
  templateUrl: './edit-location-page.component.html',
  styleUrls: ['./edit-location-page.component.scss']
})
export class EditLocationPageComponent implements OnInit {
  editLocationForm:FormGroup;
  name = '';
  addressLine = '';
  city = '';
  state = '';
  zipcode = '';
  contactNumber = '';
  vehicleCapacity = '';

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();
  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,
      private api: ApiService) { }

  ngOnInit(): void {
    this.getLocationById(this.actr.snapshot.params.id);

    this.editLocationForm = this.formBuilder.group({
      id : [null, [Validators.required]],
      address_id : [null, [Validators.required]],
      name : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      addressLine : [null, [Validators.required,Validators.pattern("^(?:[A-Za-z0-9]+)(?:[A-Za-z0-9 _]*)$")]],
      city : [null, [Validators.required, Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      state : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      zipcode : [null, [Validators.required,Validators.pattern("^[0-9]+$"),Validators.maxLength(5)]],
      contactNumber : [null, [Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(10)]],
      vehicleCapacity : [null, [Validators.required,Validators.pattern("^[0-9]+$")]]

    });
  }

  onFormSubmit() {
    this.isLoadingResults = true;
    //this.router.navigate(['/login']);

    var formData = {}
    formData['id'] = this.editLocationForm.get('id').value
    formData['contactNumber'] = this.editLocationForm.get('contactNumber').value
    formData['name'] = this.editLocationForm.get('name').value
    formData['vehicleCapacity'] = this.editLocationForm.get('vehicleCapacity').value
    var address = {}
    address['id'] = this.editLocationForm.get('address_id').value
    address['street'] = this.editLocationForm.get('addressLine').value
    address['city'] = this.editLocationForm.get('city').value
    address['state'] = this.editLocationForm.get('state').value
    address['zipCode'] = this.editLocationForm.get('zipcode').value
    formData['address'] = address;
    this.api.updateLocationAPI(formData).subscribe((res:any)=>{
      this.router.navigate(['/locations']);
    });
  }


  getLocationById(id: any) {
    var object = this;
   this.api.getLocationById(id).subscribe((data: any) => {

     console.log(data)
     this.editLocationForm.setValue({
       id : data.id,
       name : data.name,
       addressLine : data.address.street,
       city :  data.address.city,
       state :  data.address.state,
       zipcode : data.address.zipCode,
       contactNumber : data.contactNumber,
       vehicleCapacity : data.vehicleCapacity,
       address_id : data.address.id



     });
   });
 }

 closeWindow(){
     this.router.navigate(['/locations']);
 }
}
