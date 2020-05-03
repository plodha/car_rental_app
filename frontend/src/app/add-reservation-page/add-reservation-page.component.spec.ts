import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReservationPageComponent } from './add-reservation-page.component';

describe('AddReservationPageComponent', () => {
  let component: AddReservationPageComponent;
  let fixture: ComponentFixture<AddReservationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddReservationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddReservationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
