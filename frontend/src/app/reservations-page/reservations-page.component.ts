import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';


export interface Reservation {
  id:number;
  vehicleInformation: string;
  pickupDateTime: string;
  dropoffDateTime: string;
  estimatedPrice: string;
  status:boolean;


}
const ELEMENT_DATA: Reservation[] = [
  {id: 1, vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 2, vehicleInformation:'Audi Q8',pickupDateTime: '05/01/2020', dropoffDateTime:'05/04/2020', estimatedPrice:'$ 83.68', status:true},
  {id: 3,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 4,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 5,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 6,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 7,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 8,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 9,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
  {id: 10,  vehicleInformation:'Audi A8',pickupDateTime: '10/01/2019', dropoffDateTime:'12/01/2019', estimatedPrice:'$ 123.68', status:false},
];
@Component({
  selector: 'app-reservations-page',
  templateUrl: './reservations-page.component.html',
  styleUrls: ['./reservations-page.component.scss']
})
export class ReservationsPageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'vehicleInformation', 'pickupDateTime','dropoffDateTime','estimatedPrice','status'];
  dataSource: MatTableDataSource<Reservation>;


  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  constructor() {
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);

  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
