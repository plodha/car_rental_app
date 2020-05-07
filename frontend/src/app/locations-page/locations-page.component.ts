import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {ActivatedRoute, Router } from '@angular/router';


import {ApiService} from '../api.service'

export interface Location {
  id:number;
  name: string;
  addressLine: string;
  city: string;
  state: string;
  zipcode:string;
  contactNumber:string;
  vehicleCapacity:number;
  addressId : number;

}
/*
const ELEMENT_DATA: Location[] = [
  {id: 1, name:'Sunnyvale Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 2, name:'San Jose Suite',addressLine: '28 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 3, name:'San Antonio Suite',addressLine: '26 Vinney drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'1321312312',vehicleCapacity:10},
  {id: 4, name:'MountainView Suite',addressLine: '128 Washington drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 5, name:'Fremont Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 6, name:'Union city Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 7, name:'Menlo Park Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 8, name:'Gilbrae Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 9, name:'Milroy Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 10, name:'Palo alto Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
];
*/
@Component({
  selector: 'app-locations-page',
  templateUrl: './locations-page.component.html',
  styleUrls: ['./locations-page.component.scss']
})
export class LocationsPageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'addressLine','city','state','zipcode', 'contactNumber','vehicleCapacity','star'];
  dataSource: MatTableDataSource<Location>;
  ELEMENT_DATA: Location[] = [];
  isLoadingResults = false;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {
    this.actr.data.subscribe((data)=>{
      var locations = data.location
      var i = 0;
      for (i = 0; i < locations.length; i++) {

        var obj = locations[i]
        var location = {
          id: obj.id,
          name:obj.name,
          addressLine: obj.address.street,
           city:obj.address.city,
           state:obj.address.state,
           zipcode:obj.address.zipCode,
            contactNumber:obj.contactNumber,
            vehicleCapacity:obj.vehicleCapacity,
            addressId : obj.address.id
        }
        this.ELEMENT_DATA[i] = location;
      }
    });
    this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
    // console.log('datasuce ');
    // console.log(this.dataSource)
  }

  ngOnInit(): void {



    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    console.log(localStorage.username != undefined)
  if(localStorage.username != undefined ) {
    document.getElementById('profileName').innerHTML += localStorage.username
  }
  else {
    this.router.navigate(['/login']);

  }

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  fetchAllLocations(){
    this.api.getAllLocations().subscribe((data:any) => {
      console.log("result")
      console.log(data[0]);
      var i = 0;
      for (i = 0; i < data.length; i++) {

        var obj = data[i]
        console.log(obj)
        var location = {
          id: obj.id,
          name:obj.name,
          addressLine: obj.address.street,
           city:obj.address.city,
           state:obj.address.state,
           zipcode:obj.address.zipCode,
            contactNumber:obj.contactNumber,
            vehicleCapacity:obj.vehicleCapacity,
            addressId : obj.address.id
        }
        this.ELEMENT_DATA[i] = location;

      }

    /*

      "address": {
                 "state": "CA",
                 "id": 1,
                 "city": "random city",
                 "street": "random street",
                 "zipCode": 94086
             },
             "id": 1,
             "vehicleCapacity": 10,
             "contactNumber": 1234567890,
             "name": "America"
*/

    })
  }


  updateLocation(operation, ele){
    console.log('update ');
    console.log(ele)
    if(operation == 'Update') {
      this.router.navigate(['/editLocation/'+ele.id]);
    }
    if(operation == 'Delete') {
      var formData = {}
      formData['id'] = ele.id;
      this.isLoadingResults = false;
      this.api.deleteLocation(formData).subscribe((res:any) => {
        console.log(res);
          this.isLoadingResults = false;

          //this.router.navigate(['/vehicle']);

        this.fetchAllLocations();


        });

        location.reload();
      //this.router.navigate(['/editVehicle/'+ele.id]);
    }


  }

}
