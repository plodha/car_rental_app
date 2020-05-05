import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerLandingPageComponent } from './customer-landing-page.component';

describe('CustomerLandingPageComponent', () => {
  let component: CustomerLandingPageComponent;
  let fixture: ComponentFixture<CustomerLandingPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerLandingPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerLandingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
