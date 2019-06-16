/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { LeadService } from 'app/entities/lead/lead.service';
import { ILead, Lead } from 'app/shared/model/lead.model';

describe('Service Tests', () => {
  describe('Lead Service', () => {
    let injector: TestBed;
    let service: LeadService;
    let httpMock: HttpTestingController;
    let elemDefault: ILead;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(LeadService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Lead(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Lead', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Lead(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Lead', async () => {
        const returnedFromService = Object.assign(
          {
            leadName: 'BBBBBB',
            leadPhone: 'BBBBBB',
            leadid: 'BBBBBB',
            title: 'BBBBBB',
            status: 'BBBBBB',
            active: 'BBBBBB',
            hotel: 'BBBBBB',
            rooms: 'BBBBBB',
            contact: 'BBBBBB',
            officetel: 'BBBBBB',
            cell: 'BBBBBB',
            street1: 'BBBBBB',
            street2: 'BBBBBB',
            city: 'BBBBBB',
            state: 'BBBBBB',
            postalcode: 'BBBBBB',
            country: 'BBBBBB',
            website: 'BBBBBB',
            email: 'BBBBBB',
            followup: 'BBBBBB',
            comment: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Lead', async () => {
        const returnedFromService = Object.assign(
          {
            leadName: 'BBBBBB',
            leadPhone: 'BBBBBB',
            leadid: 'BBBBBB',
            title: 'BBBBBB',
            status: 'BBBBBB',
            active: 'BBBBBB',
            hotel: 'BBBBBB',
            rooms: 'BBBBBB',
            contact: 'BBBBBB',
            officetel: 'BBBBBB',
            cell: 'BBBBBB',
            street1: 'BBBBBB',
            street2: 'BBBBBB',
            city: 'BBBBBB',
            state: 'BBBBBB',
            postalcode: 'BBBBBB',
            country: 'BBBBBB',
            website: 'BBBBBB',
            email: 'BBBBBB',
            followup: 'BBBBBB',
            comment: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Lead', async () => {
        const rxPromise = service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
